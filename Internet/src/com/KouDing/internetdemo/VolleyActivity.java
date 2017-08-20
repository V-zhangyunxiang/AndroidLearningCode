package com.KouDing.internetdemo;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;

public class VolleyActivity extends Activity {
	RequestQueue queue = null;
	ImageView iv;
	NetworkImageView netWorkImageView;
  /**
   * Volley适用于数据简单但是请求频繁的场合,不适合大文件上传
   */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley);
		iv=(ImageView) findViewById(R.id.imageView1);
		netWorkImageView=(NetworkImageView) findViewById(R.id.net_Image);
		//创建一个请求队列
		queue = Volley.newRequestQueue(this);
		netWorkImageView();
		
	}
	//使用NetworkImageView加载图片(简单方便)
    public void netWorkImageView(){
	 netWorkImageView.setDefaultImageResId(R.drawable.ic_launcher);
	 netWorkImageView.setErrorImageResId(R.drawable.ic_launcher);
	 String url="http://www.baidu.com/img/bd_logo1.png";
	 netWorkImageView.setImageUrl(url, new ImageLoader(queue, new BitmapCache()));
	}
	// 创建一个字符串get请求
	public void sendStringRequestClick(View v) {
		String url = "http://www.baidu.com";
		StringRequest request = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						System.out.println(arg0);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println(arg0);
					}
				});
		queue.add(request);
	}

	// 发送一个带参数的post请求
	public void sendPostRequestClick(View v) {
		String url = "http://www.baidu.com";
		StringRequest request = new StringRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						System.out.println(arg0);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println(arg0);
					}
				}) {
			//用于向服务器端传递数据
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params=new HashMap<String, String>();
               params.put("name", "jj");
			   return super.getParams();
			}
		};
		queue.add(request);
		
	}
	//发送一个图片请求
	public void sendImageRequestClick(View v){
		String url="http://www.baidu.com/img/bd_logo1.png";
		ImageRequest request=new ImageRequest(url, new Listener<Bitmap>() {
         //0表示不管多大,都不会压缩. ARGB_8888展示最好的颜色
			@Override
			public void onResponse(Bitmap response) {
				 iv.setImageBitmap(response);
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		queue.add(request);
	}
	//使用ImageLoader加载图片(缓存)
	public void sendImageRequestSpeedClick(View v){
		String url="http://www.baidu.com/img/bd_logo1.png";
		ImageLoader imageLoader=new ImageLoader(queue,new BitmapCache());
		//获取图片监听器
		//view组件,defaultImageResId,errorImageResId
		ImageLoader.ImageListener listener=ImageLoader.getImageListener(iv, R.drawable.ic_launcher, R.drawable.ic_launcher);
	    imageLoader.get(url, listener,0,0);//数字表示最大宽高.0,0表示不限制
	}
	//用于ImageLoader图片缓存
	private static class BitmapCache implements ImageLoader.ImageCache{
        private LruCache<String, Bitmap> cache;//内存缓存
        private int maxCache=16*1024*1024;//16M(是一个Activity的最小缓存大小)
        public BitmapCache(){
        	cache=new LruCache<String, Bitmap>(maxCache);
        }
		@Override
		public Bitmap getBitmap(String url) {
			return cache.get(url);
		}
		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			cache.put(url, bitmap);
		}
	}
}
