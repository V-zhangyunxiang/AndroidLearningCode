package com.example.menusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class PopupActivity extends Activity{
	//弹出式菜单
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_menus);
	}
    public void Click(View v){
    	PopupMenu pm=new PopupMenu(this, v);
    	getMenuInflater().inflate(R.menu.popup_menus, pm.getMenu());
    	pm.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.big:
					Toast.makeText(PopupActivity.this, "大", Toast.LENGTH_SHORT).show();
					break;
				case R.id.middle:
					Toast.makeText(PopupActivity.this, "中", Toast.LENGTH_SHORT).show();
					break;
				case R.id.small:
					Toast.makeText(PopupActivity.this, "小", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				return false;
			}
		});
    	pm.show();
    	
    }
}
