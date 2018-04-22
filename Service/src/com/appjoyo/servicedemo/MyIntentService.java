package com.appjoyo.servicedemo;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {
	/**
	 * 1.IntentService是继承于Service并处理异步请求的一个类,在其中有一个工作线程来处理耗时操作
	 * 启动方法与Service一样,当任务执行完成后,其会自动停止.
	 * 2.可以启动多次,每一个耗时操作会以队列的方式,依次在其onHandleIntent()回调方法中执行. 
	 * 3.通常用该类完成本app内部的耗时操作
	 * 
	 * 
	 */

	public MyIntentService() {
		super("MyIntentService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// 可以用intent接收值
		for (int i = 0; i < 40; i++) {
			System.out.println("onHandleIntent" + i + "-"
					+ Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
