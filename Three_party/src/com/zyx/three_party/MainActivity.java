package com.zyx.three_party;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//创建侧滑菜单
		SlidingMenu menu=new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
//     	menu.setMode(SlidingMenu.LEFT_RIGHT);//左右都有菜单
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	    menu.setFadeDegree(0.55f);
	    menu.setMenu(R.layout.activity_slidingmenu);
//	    menu.setSecondaryMenu(R.layout.activity_main);//设置右菜单
	    menu.setBehindScrollScale(1.0f);//设置菜单随滑动慢慢进入(1),还是已经固定好位置(0)
	    menu.setBehindOffsetRes(R.dimen.menu_offset);//设置偏移量,离滑动方向的距离
	    menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	}



}
