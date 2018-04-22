package com.example.menusdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;

public class ContextActivity extends Activity{
	//上下文菜单
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context_menus);
		tv=(TextView) findViewById(R.id.text);
		registerForContextMenu(tv);//注册某个view的监听事件
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_menus, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.red:
			tv.setBackgroundColor(Color.RED);
			break;
		case R.id.blue:
			tv.setBackgroundColor(Color.BLUE);
			break;
		case R.id.yellow:
			tv.setBackgroundColor(Color.YELLOW);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

}
