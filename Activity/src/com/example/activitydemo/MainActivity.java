package com.example.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	/**
	 * Activity3大状态:可交互;暂停;停止 
	 * Activity7大生命周期: 
	 * onCreate()创建 
	 * onStart()用户可见
	 * onResume()用户可交互 
	 *                 -onRestart()重新可见 
	 * onPause()失去焦点 
	 * onStop()页面(完全)不可见
	 * onDestory()页面被销毁 一个应用程序就是一个Activity栈,所有活动依赖于Activity,为先进后出模式
	 * 当从onPause()状态返回Activity时,会从onResume()开始
	 * 当一个Activity处于onPause()和onStop()状态时,可能会被直接销毁,因为打开其他应用后内存不够,被迫销毁.
	 */
 EditText ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed=(EditText) findViewById(R.id.editText1);
	}

	public void send(View v) {
		Intent intent = new Intent(this, MainActivityB.class);
		String value=ed.getText().toString();
		//1.第一种传值方式
		intent.putExtra("msg", value);
		//2 第二种bundle传值方式
		Bundle bundle=new Bundle();//放在集合里
		bundle.putString("code", "1001");
		intent.putExtra("bundle", bundle);//要把bundle放到intent里
		startActivity(intent);
	}
	//Serializable序列化传递对象,效率较低
	public void sendObject(View v){
		Cat cat=new Cat();
		cat.setName("小花");
		cat.setAge(6);
		cat.setType("莫阿猫");
		Intent intent=new Intent(this, MainActivityB.class);
		intent.putExtra("cat", cat);
		startActivity(intent);
	}
	//Parcelable包裹传递对象,效率较高
	public void sendObject2(View v){
		Dog d=new Dog();
		d.setName("旺财");
		d.setAge(6);
		d.setType("柯基狗");
		Intent intent=new Intent(this, MainActivityB.class);
	    intent.putExtra("dog", d);
	    startActivity(intent);
	}
	//Activity回传值。。。
}
