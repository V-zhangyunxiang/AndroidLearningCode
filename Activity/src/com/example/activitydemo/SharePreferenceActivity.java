package com.example.activitydemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.EditText;

public class SharePreferenceActivity extends Activity {
	private SharedPreferences sp;
	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharepreference);
		et = (EditText) findViewById(R.id.editText1);
		//获取当前程序的SharedPreferences对象
		sp=getSharedPreferences("msg", Activity.MODE_PRIVATE);
	}

	@Override
	protected void onPause() {
		System.out.println("onPause");
		super.onPause();
		String msg=et.getText().toString();
		if(msg==null||msg.equals("")){
			return;
		}
		Editor ed=sp.edit();
		ed.putString("msg", msg);
		ed.commit();
		//ed.apply();
	}

	@Override
	protected void onResume() {
		System.out.println("onResume");
		super.onResume();
		et.setText(sp.getString("msg", ""));
		//还原完成后删除原有旧信息
		Editor ed=sp.edit();
		ed.remove("msg");
		ed.commit();
	}
    @Override
    protected void onRestart() {
    	System.out.println("onRestart");
    	super.onRestart();
    }
    @Override
    protected void onStart() {
    	System.out.println("onStart");
    	super.onStart();
    }
	@Override
	protected void onStop() {
		System.out.println("onStop");
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
}
