package com.appjoyo.broadcastdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	MyReceive2 receiver;

	/**
	 * 1.静态注册的广播属于常驻型,动态注册的广播可以随时取消
	 * 2.有序广播中优先级高的广播接收器可以往低优先级广播接收器传递数据,也可以中断广播;有序广播也可动态注册.
	 * 3.接收顺序:设置了优先级的高低>动态注册>静态注册
	 * 4.粘性广播发送后会一直存在,即使延迟打开接收器也能接收到,除非手动取消
	 * 5.广播接收器为10秒有效期,onReceive方法执行完了,就销毁 ,为了程序的健壮性,具体代码应该放在服务中
	 *   
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		receiver = new MyReceive2();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.appjoyo.action.MY_ACTION1");
		registerReceiver(receiver, filter);

	}
	@Override
	protected void onResume() {
		//立即显示电量变化. 
		//动态注册广播时,类似这种系统广播,不需要接收器
		IntentFilter filter=new IntentFilter();
		filter.addAction("android.intent.action.BATTERY_CHANGED");
		Intent batteryIntent=getApplicationContext().registerReceiver(null, filter);
		int curr=batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
	    int total=batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
	    int percent=curr*100/total;
	    Toast.makeText(this, "当前电量为:"+percent+"%", 0).show();
		super.onResume();
	}
    //普通广播
	public void sendBroadcast(View v) {
		Intent intent = new Intent("com.appjoyo.action.MY_ACTION1");
		intent.putExtra("msg", "静态注册广播");
		sendBroadcast(intent);
	}
    //取消动态注册的广播
	@Override
	protected void onStop() {
		unregisterReceiver(receiver);
		super.onStop();
	}
    //有序广播
	public void sendOrderBroadcast(View v) {
		Intent intent = new Intent("com.appjoyo.action.MY_ACTION2");
		sendOrderedBroadcast(intent, null);
	}
	
	//粘性广播
	public void sendStickBroadcast(View v){
		Intent intent=new Intent("com.appjoyo.action.MY_ACTION5");
		sendStickyBroadcast(intent);
	}
	//打开粘性广播接收方
	public void openStickBroadcast(View v){
		Intent intent=new Intent(this,MainActivity5.class);
		startActivity(intent);
		
		
	}

}
