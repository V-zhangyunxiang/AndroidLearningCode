package com.example.activitydemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ScreenOrientationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 以下为通过代码设置,必须在布局之前,也可以通过设置AndroidManifest.xml来实现
		// 横竖屏
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		// 设置全屏模式
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 去除标题栏
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screenorientation);
	}
}
