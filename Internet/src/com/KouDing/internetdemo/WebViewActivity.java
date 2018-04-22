package com.KouDing.internetdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends Activity{
	WebView webView;
	ProgressBar progressBar;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);
		webView=(WebView) findViewById(R.id.webView);
		progressBar=(ProgressBar) findViewById(R.id.progressbar);
		//在当前浏览器打开
		webView.setWebViewClient(new WebViewClient());
		handler=new Handler();
                webView.requestFocus();//不设置,会出现软键盘不能弹出的问题
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条在内部显示
		WebSettings webSettings=webView.getSettings();
		webSettings.setJavaScriptEnabled(true);//开启JavaScript支持
		webSettings.setAppCacheEnabled(true);  //开启缓存

                //设置自适应屏幕，两者合用
                webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小 
                webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
                //支持插件
                webSettings.setPluginsEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存应用模式
		//WebView与JavaScript的交互
		webView.addJavascriptInterface(new Object(){
			@JavascriptInterface //这个注解不能少
			public void clickAndroid(){
				handler.post(new Runnable() {
					@Override
					public void run() {
					webView.loadUrl("javascript:myfun()");
					}
				});
			}
		}, "demo");   //demo为object类的别名
		webView.loadUrl("file:///android_asset/index.html");
//		webView.loadUrl("http://www.baidu.com");
		webView.setWebChromeClient(new WebChromeClient(){
			//可以设置标题栏进度
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if(newProgress<100){
				progressBar.setProgress(newProgress);
				progressBar.setVisibility(View.VISIBLE);
				}
				if(newProgress==100)
				progressBar.setVisibility(View.GONE);
				super.onProgressChanged(view, newProgress);
			}
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
			}
			
		});
	}
	//监听键盘返回键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (webView.canGoBack()) {  //判断能否返回上一页
				webView.goBack();//返回上一页
				return true;     //拦截
			}else{
				return super.onKeyDown(keyCode, event);//放行
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}

