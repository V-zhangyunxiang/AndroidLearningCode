package com.appjoyo.servicedemo;

import com.appjoyo.servicedemo.ICat.Stub;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
   
	private boolean mBound=false;//是否绑定 
	private ICat icat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startService(View v) {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);
	}

	public void stopService(View v) {
		Intent intent = new Intent(this, MyService.class);
		stopService(intent);
	}
	public void startIntentServiceClick(View v) {
		Intent intent = new Intent(this, MyIntentService.class);
	    startService(intent);
	   
	}
	public void bindService(View v){
		Intent intent = new Intent(this, MyBindService.class);
		//异步绑定,绑定成功后会回调onServiceConnected方法
	    bindService(intent, conn, Context.BIND_AUTO_CREATE);
	   
	}
	public void unBindService(View v){
		if(mBound){
		unbindService(conn);
		mBound=false;
		}
		Toast.makeText(MainActivity.this, "解绑成功", 0).show();
	   
	}
	//绑定服务的连接回调接口
	private ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			//服务异常时调用
			mBound=false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//绑定成功后回调的方法
			icat=Stub.asInterface(service);
			System.out.println("icat:"+icat);
			mBound=true;
			Toast.makeText(MainActivity.this, "绑定成功bind", 0).show();
		}
	};
	public void callClick(View v){
		if(icat==null){
			return;
		}
		try {
			icat.setName("miaomiao");
			System.out.println(icat.desc());
			System.out.println(icat.getPerson().toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	//------------线程安全的，不支持并发处理
	private ServiceConnection conn2=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			flag=false;
		} 
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			messenger=new Messenger(service);
			System.out.println("messenger:"+messenger);
			flag=true;
			Toast.makeText(MainActivity.this, "绑定成功Mess", 0).show();
		}
	};
	protected void onStart() {
		Intent intent=new Intent(this,MessengerService.class);
		bindService(intent,conn2,Context.BIND_AUTO_CREATE);
		super.onStart();
	};
	protected void onStop() {
		if(flag){
			unbindService(conn2);
			flag=false;
			Toast.makeText(MainActivity.this, "解绑成功Mess", 0).show();
		}
		super.onStop();
	};
	Messenger messenger;
	boolean flag=false;
	//使用Messenger来进行IPC
	public void messengerClick(View v){
		if(messenger==null){
			return;
		}
		Message msg=Message.obtain();
		msg.what=MessengerService.SAY_HELLO;
		msg.obj="履历加油";
		try {
			messenger.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	}
