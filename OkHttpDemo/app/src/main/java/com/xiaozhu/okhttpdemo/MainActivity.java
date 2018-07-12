package com.xiaozhu.okhttpdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/plain; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_execute = findViewById(R.id.btn);
        Button btn_enqueue = findViewById(R.id.button);
        Button btn_string = findViewById(R.id.button2);
        Button btn_stream = findViewById(R.id.button3);
        Button btn_file = findViewById(R.id.button4);
        Button btn_form = findViewById(R.id.button5);
        Button btn_multipart = findViewById(R.id.button6);
        //缓存
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        String CachePath = getCacheDir().getPath() + File.separator + "okhttp";
        File Cache = new File(CachePath);
        if (!Cache.exists()) {
            Cache.mkdirs();
        }
        Cache cache = new Cache(Cache, cacheSize);

        final OkHttpClient client = new OkHttpClient.Builder()
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                //身份验证
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@NonNull Route route, @NonNull Response response) {
                        //response.challenges();可得到第一次失败时的授权信息
                        //如果 Response 的响应码是 407 proxy unauthorized; 使用 OkHttpClient.Builder的proxyAuthenticator()方法
                        String credential = Credentials.basic("jesse", "password");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();

        final Request request = new Request.Builder()
                .url("http://www.baidu.com/")
                //设置是否使用缓存
                .cacheControl(CacheControl.FORCE_NETWORK)
                //调用header(name, value)方法就可以设置请求头的 name 和 value，调用该方法会确保整个请求头中不会存在多个名称一样的 name。
                //如果想添加多个 name 相同的请求头，应该调用addHeader(name, value)方法，这样可以添加重复 name 的请求头，其 value 可以不同
                /*.header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")*/
                .build();

        //同步 Get，因为是同步，所以需要在子线程进行
        btn_execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Response response;
                        try {
                            response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                Headers responseHeaders = response.headers();
                                for (int i = 0; i < responseHeaders.size(); i++) {
                                    Log.i("responseHeaders", responseHeaders.name(i) + ":" + responseHeaders.value(i));
                                }
                                if (response.body() != null) {
                                    Log.i("body", response.body().string());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        //异步 Get
        btn_enqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //在工作线程
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        //在工作线程，更新 UI 时需要使用 runOnUiThread/view.post/handler.post
                        if (response.isSuccessful()) {
                            Headers responseHeaders = response.headers();
                            for (int i = 0; i < responseHeaders.size(); i++) {
                                Log.i("responseHeaders", responseHeaders.name(i) + ":" + responseHeaders.value(i));
                            }
                            if (response.body() != null) {
                                Log.i("body", response.body().string());
                            }
                        }
                    }
                });
            }
        });

        //        json ：application/json
        //        xml：application/xml
        //        png：image/png
        //        jpg： image/jpeg
        //        gif：image/gif

        //post 发送 String
        btn_string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postBody = "{username:admin;password:admin}";
                Request request1 = new Request.Builder()
                        .url("http://www.baidu.com/")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                        .build();
                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            Log.i("body", response.body().string());
                        }
                    }
                });
            }
        });

        //post 发送 Stream 流
        btn_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody requestBody = new RequestBody() {
                    @Nullable
                    @Override
                    public MediaType contentType() {
                        return MEDIA_TYPE_MARKDOWN;
                    }

                    @Override
                    public void writeTo(@NonNull BufferedSink sink) throws IOException {
                        sink.writeUtf8("Numbers\n");
                        //sink.outputStream();
                        //通过 BufferedSink 的各种 write 方法向其写入各种类型的数据，此例中用其 writeUtf8 方法向其中写入UTF-8的文本数据。
                        //也可以通过它的 outputStream()方法，得到输出流 OutputStream，从而通过 OutputStream 向 BufferedSink 写入数据。
                    }
                };
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(requestBody)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (response != null) {
                        response.close();
                        //todo 关闭响应体
                    }
                }

            }
        });

        //post 发送 file
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("README.md");
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))//传递 file
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (response != null) {
                        response.close();
                        //关闭响应体
                    }
                }

            }
        });

        //post 发送 Form 表单中的键值对
        //FormBody 继承自 RequestBody
        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody formBody = new FormBody.Builder()
                        .add("search", "Jurassic Park")
                        .build();
                Request request = new Request.Builder()
                        .url("https://en.wikipedia.org/w/index.php")
                        .post(formBody)
                        .build();
                Response response;
                try {
                    response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // post 发送分块请求
        btn_multipart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IMGUR_CLIENT_ID = "...";
                MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        //前者添加的是字符串键值对数据，后者可以添加文件。
                        .addFormDataPart("title", "Square Logo")
                        .addFormDataPart("image", "logo-square.png", RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                        .build();

                Request request = new Request.Builder()
                        .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                        .url("https://api.imgur.com/3/image")
                        .post(requestBody)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    System.out.println(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        //取消请求
        //  Call call = client.newCall(request);
        //  call.cancel();

    }


}
