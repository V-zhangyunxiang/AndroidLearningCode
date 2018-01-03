package com.appjoyo.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
	/**
	 * 默认情况下Service与主线程在同一个进程中的同一个线程中执行
	 * 如果服务执行耗时操作,需使用子线程来完成工作,避免主线程阻塞
	 */
	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	// 当启动一个已启动的服务,会直接调用onStartCommand方法
	@Override
	public void onCreate() {
		System.out.println("onCreate");
		super.onCreate();
	}

	// 在该方法内实现服务的核心业务
	//返回4个int类型的状态
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
         //可以通过intent接收值
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					System.out.println("onStartCommand" + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == 30) {
						MyService.this.stopSelf();// 自动终止自己
					}
				}

			}
		}).start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}

}
