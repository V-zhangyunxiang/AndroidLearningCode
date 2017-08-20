package com.example.dialog_basic;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	// 提示对话框
	public void Click1(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("吃饭了没");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("有", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, "吃了", Toast.LENGTH_SHORT)
			.show();
			}
		});
		builder.setNegativeButton("没有", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, "么吃", Toast.LENGTH_SHORT)
			.show();
			}
		});
		builder.show();
	}
	// 列表对话框
	public void Click2(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择一个系统:");
		final String[] items = { "Android", "ios", "windows phone" };
		builder.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, items[arg1], 0).show();
			}
		});
		builder.show();
	}
	// 多项多选对话框
	public void Click3(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择一个或多个系统:");
		final ArrayList<String> list = new ArrayList<String>();
		final String[] items = { "Android", "ios", "windows phone" };
		builder.setMultiChoiceItems(items, null,new OnMultiChoiceClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1,boolean arg2) {
		if (arg2){
		list.add(items[arg1]);
		} else {
		list.remove(items[arg1]);
		}
		 }
			});
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, list.toString(),Toast.LENGTH_SHORT).show();
				arg0.dismiss();// 关掉对话框
			}
		});
		builder.setNegativeButton("No", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				list.clear();
				arg0.dismiss();
			}
		});
		builder.show();
	}
	// 多项单选对话框
	String result = null;
	public void Click4(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择一个系统:");
		final String[] items = { "Android", "ios", "windows phone" };
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {
			// 0是默认选中android
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			result = items[arg1];
			Toast.makeText(MainActivity.this, "你选中了" + result,Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
				arg0.dismiss();// 关掉对话框
			}
		});
		builder.setNegativeButton("No", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		builder.show();
	}
	// 自定义对话框
	public void Click5(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("登录对话框");
		// 布局文件转化为View视图
		final View view = getLayoutInflater().inflate(R.layout.login_in, null);
		builder.setView(view);
		builder.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				EditText edit1 = (EditText) view.findViewById(R.id.username);
				EditText edit2 = (EditText) view.findViewById(R.id.password);
				String username = edit1.getText().toString();
				String password = edit2.getText().toString();
				Toast.makeText(MainActivity.this, username + "-" + password,Toast.LENGTH_SHORT).show();
				arg0.dismiss();// 关掉对话框
			}
		});
		builder.setNegativeButton("No", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		builder.show();
	}

}
