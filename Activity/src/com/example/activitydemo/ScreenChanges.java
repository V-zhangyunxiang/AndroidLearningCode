package com.example.activitydemo;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class ScreenChanges extends Activity{
	//当横竖屏切换时,直接执行onCreate(),参数将被初始化
	//Bundle savedInstanceState就是为了释放被保存的参数
	//虽然以上方法可以解决数据丢失问题,但是反复创建Activity会影响性能
	/*
	 * 在AndroidManifest.xml中设置android:configChanges的值
	 * 这样横竖屏切换时就不会反复启动Activity,而是调用onConfigurationChanged函数
	 * 就不需要再保存了
	 */
	//
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_changes);
		System.out.println("---onCreate");
		//还原被保存的index的值
		if(savedInstanceState!=null){
			index=savedInstanceState.getInt("index");
		}
	}

	public void onClick(View v){
		index++;
		System.out.println("index:"+index);
	}
	
	//保存相关的数据
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		System.out.println("onSaveInstanceState");
		outState.putInt("index", index);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
	}
}
