package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive7 extends BroadcastReceiver {
   //监听网络变化
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		System.out.println("info:"+info);
		if (info != null) {
			String name = info.getTypeName();
			Toast.makeText(context, name, 0).show();
		}
	}

}
