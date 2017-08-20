package com.example.popupwindow;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn, btn1;
	PopupWindow popupwindow;
	LinearLayout top_lin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn);
		//将popupwindow的布局转化为view对象
		View view = LayoutInflater.from(this).inflate(R.layout.pop_item, null);
		//获取popup内的按钮
		btn1 = (Button) view.findViewById(R.id.btn1);
		top_lin = (LinearLayout) view.findViewById(R.id.top_lin);
		//获取popupwidow对象
		popupwindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		//点空白销毁
		popupwindow.setFocusable(true);
		popupwindow.setOutsideTouchable(true);
		popupwindow.setTouchable(true);
		//防止虚拟键盘挡住
		popupwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		//设置背景图片
		popupwindow.setBackgroundDrawable(new BitmapDrawable());
		//动画效果
		popupwindow.setAnimationStyle(R.style.popupAnimation);//也可以用系统的
		//点击弹出popupWindow
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showpopup(); 
			}
		});
		//popupwindow内部的按钮
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			Toast.makeText(MainActivity.this, "可以好好玩了", Toast.LENGTH_LONG)
			.show();
			}
		   });
		//popupwindow内部无内容布局的按钮
		top_lin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			popupwindow.dismiss();//销毁popupWindow
			}
		});
		//获取屏幕尺寸
//		DisplayMetrics dm=new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int width=dm.widthPixels;
//		int height=dm.heightPixels;
	}
	
	public void showpopup() {
		//显示popupWindow
		//1.相对于某个控件的位置
		//popupwindow.showAsDropDown(btn, 10, 50);
		//2.相对于父控件的位置 
		//第一个参数调用其getWindowToken()方法获取窗口的Token,所以只要是该窗口上的控件就可以了.
		popupwindow.showAtLocation(btn, Gravity.BOTTOM, 0, 0);
		//该方法x正往右，y正往下;在底部时,y正往上移

	}

}
