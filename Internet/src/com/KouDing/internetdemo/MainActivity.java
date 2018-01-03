package com.KouDing.internetdemo;

import java.lang.ref.WeakReference;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
/**
 * Http协议通常承载于TCP协议之上,有时也承载于TLS或SSL协议层之上,这时就是Https
 * Http默认端口号:80,Https默认端口号:443
 * 数据链路层-->IP-->TCP-->HTTP(TLS/SSL)
 * RFC 2616版本就是现在的Http1.1
 * URL是一种具体的URI,它不仅唯一标识资源,而且还提供了定位该资源的信息
 * URI是一种语义上的抽象概念,可以是绝对的,也可以是相对的,而URL则必须提供足够的信息来定位
 * 
 */
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView) findViewById(R.id.imageView1);
		//判断一个字符串是否为空或空字符串
		//TextUtils.isEmpty(str);
	}
	
  public void checkNetClick(View v ){
	 boolean bool=isNetWorkConnected(this);
	 if(bool){
		 Toast.makeText(this, "网络可用", Toast.LENGTH_SHORT).show();
	 }else{
		 Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
	 }
  }
  //检查网络是否可用
  public boolean isNetWorkConnected(Context context){
	  if(context!=null){
		ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getActiveNetworkInfo();
	    if(ni!=null){
	    	return ni.isAvailable();
	    }
	  }
	return false;
	  
  }
  //判断是否wifi是否可用
  public boolean isWifiWorkConnected(Context context){
	  if(context!=null){
		ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    if(ni!=null){
	    	return ni.isAvailable();
	    }
	  }
	return false;
	  
  }
  //判断Mobile是否可用
  public boolean isMobileWorkConnected(Context context){
	  if(context!=null){
		ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    if(ni!=null){
	    	return ni.isAvailable();
	    }
	  }
	return false;
	  
  }
	//判断是哪种类型的网络
  public static int getConnectedType(Context context){
	  if(context!=null){
			ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getActiveNetworkInfo();
		    if(ni!=null && ni.isAvailable()){
		    	return ni.getType();
		    }
		  }
	return -1;
	  
  }
  public void showImage(View v){
	 new Thread(new Runnable() {
		@Override
		public void run() {
			//访问图片网址
//			Bitmap bitmap=HttpGet.getInstance().getImageURL("http://www.baidu.com/img/bd_logo1.png");
//	        Message msg=Message.obtain();
//	        msg.what=100;
//	        msg.obj=bitmap;
//	        handler.sendMessage(msg);
	       //访问文本网址
			String data= HttpGet.getInstance().getURL("http://www.baidu.com");
			System.out.println(data);
		}
	}).start();
  }
    //该写法为标准的handler写法,避免了内存泄漏问题
	private MyHandler handler=new MyHandler(this);//传递外部类对象
	private static class MyHandler extends Handler {
		WeakReference<MainActivity> weakReference;
        //activity指代外部类的对象
		public MyHandler(MainActivity activity) {
			weakReference = new WeakReference<MainActivity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			MainActivity activity = weakReference.get();//得到外部类对象
			if (activity != null) {
				switch (msg.what) {
				case 100:
					activity.iv.setImageBitmap((Bitmap)msg.obj);
					break;

				default:
					break;
				}
			}
			super.handleMessage(msg);
		}
	}

}
