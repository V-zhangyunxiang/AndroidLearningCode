package com.sensorsdata.heatmap;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangyunxiang on 2017/8/9.
 */

public class RetrofitManager {
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static final int DEFAULT_WRITE_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private static Context mContext;
    private Cache cache;

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance(Context context) {
        mContext = context;
        return SingletonHolder.INSTANCE;
    }

    //清除 Activity 引用
    public void clearContext() {
        mContext = null;
    }

    //清除缓存
    public void clearCache() throws IOException {
        cache.evictAll();
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    private RetrofitManager() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 指定缓存路径,缓存大小100Mb
        cache = new Cache(new File(mContext.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);


        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(CacheInterceptor)
                //.addInterceptor(new AuthorizationInterceptor())
                .retryOnConnectionFailure(true)
                .cache(cache)
                .build();

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
        .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.douban.com/v2/movie/")
                .build();

    }

    /**
     * 缓存的拦截器
     */
    private static Interceptor CacheInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (isNetWorkConnected()) {
                //String cacheControl = request.cacheControl().toString();
                int maxAge = 60 * 60;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        //.header("Cache-Control", cacheControl)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7; // tolerate 1-weeks stale
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    };

    /**
     * 请求头拦截器
     */
    public static class AuthorizationInterceptor implements Interceptor {

        private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

        @Nullable
        private static String authorization;

        public static void setAuthorization(@Nullable String token) {
            authorization = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            if (authorization == null)
                return chain.proceed(originalRequest);

            final Request authorizedRequest = originalRequest.newBuilder()
                    .removeHeader(AUTHORIZATION_HEADER_NAME)
                    .addHeader(AUTHORIZATION_HEADER_NAME, authorization)  //可添加多个请求头
                    .build();
            return chain.proceed(authorizedRequest);
        }
    }

    //检查网络是否可用
    private static boolean isNetWorkConnected() {
        if (mContext != null) {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null) {
                return ni.isAvailable();
            }
        }
        return false;
    }
}
