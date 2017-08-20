package com.example.menusdemo;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 选项菜单
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 配置文件设置菜单
		getMenuInflater().inflate(R.menu.main, menu);
		//给选项添加图标,默认的即使添加了也不显示,用反射调用setOptionalIconsVisible显示
		try {
			Class<?> claz=Class.forName("com.android.internal.view.menu.MenuBuilder");
		    Method method=claz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
		    method.setAccessible(true);
		    method.invoke(menu, true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// 手动设置菜单
		/**
		 * groupId 分组 itemId 某一组子选项id order 排序 小的在上 title 子选项名称
		 */
		// menu.add(0, 1, 100, "游戏设置");//返回MenuItem类型的值
		// menu.add(0, 2, 200, "游戏开始");
		// menu.add(0, 3, 300, "游戏退出");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 得到子选项id
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:
			Toast.makeText(this, "正在设置了游戏", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_start:
			Toast.makeText(this, "正在开启了游戏", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_edit:
			Toast.makeText(this, "正在退出了游戏", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		// switch (id) {
		// case 1:
		// Toast.makeText(this, "正在设置了游戏", Toast.LENGTH_SHORT).show();
		// break;
		// case 2:
		// Toast.makeText(this, "正在开启了游戏", Toast.LENGTH_SHORT).show();
		// break;
		// case 3:
		// Toast.makeText(this, "正在退出了游戏", Toast.LENGTH_SHORT).show();
		// break;
		// default:
		// break;
		// }
		return super.onOptionsItemSelected(item);
	}

}