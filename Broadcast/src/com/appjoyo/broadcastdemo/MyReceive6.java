package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive6 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 监听开机自启广播
		Toast.makeText(context, "开机了", 0).show();
        System.out.println("开机了");
	}

}
