package com.example.intentdemo;

import java.net.URI;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	/**
	 * Intent filter匹配过程 
	 * 1.加载所有的Intent filter到列表中 
	 * 2.清除Action(动作)匹配失败的 
	 * 3.清除URI(data)匹配失败的
	 * 4.清除Category(类别)匹配失败的
	 * 5.假如还有剩下的,将匹配成功的按优先级排序
	 * 6.返回最高优先级的
	 * 间接跳转有3个门:Action,URI(data),Category,只要有1个条件不通过,就会被剔除
	 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	/**
	 * 间接查找法
	 * data(URI)属性,一般和action一起用,也必须有Category默认类别
	 * Category必须要有默认的类别,启动页除外
	 * <category android:name="android.intent.category.DEFAULT"/>
       intent打开网页的特点:
                     默认用系统内置的浏览器打开(可以自定义浏览器),并且不需要联网权限,因为浏览器自带权限
	 */
    public void dataClick(View v){
	   Intent intent=new Intent();
	   //action的内容是随意的
	   intent.setAction(Intent.ACTION_VIEW);
	   Uri data=Uri.parse("http://www.baidu.com");
	   //data与type不能单独设置，会互相清空对方
	   //两个一起用,用setDataAndType设置,type也需要在清单文件里设置
//	   intent.setData(data);
//	   intent.setType();
	   intent.setDataAndType(data, "text/html");
	   startActivity(intent);
     }
	/**
	 * 间接查找法
	 * 通过Action属性和Category属性
	 * Category必须要有默认的类别,启动页除外
	 * <category android:name="android.intent.category.DEFAULT"/>
	 */
	public void actionClick(View v) {
		Intent intent = new Intent();
		//有很多系统默认的Action(Intent.##),如打电话
		intent.setAction("com.intent.action.MY_ACTION");
		intent.addCategory("com.intent.category.MY_CATEGORY");
		startActivity(intent);

	}
	public void flagClick(View v){
		Intent intent = new Intent();
		//设置Activity的启动模式
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	// 直接查找法(通过组件名称),使用最多的跳转
	public void componentClick(View v) {
		// 跳转的简写
		Intent intent = new Intent(this, MainActivity2.class);
		startActivity(intent);
		// 跳转的详细步骤
		// Intent intent = new Intent();
		// ComponentName componentName = new ComponentName(this,
		// MainActivity2.class);
		// intent.setComponent(componentName);

	}

}
