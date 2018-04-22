package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive8 extends BroadcastReceiver {
   //监听电量变化
	@Override
	public void onReceive(Context context, Intent intent) {
		int curr=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
	    int total=intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
	    int percent=curr*100/total;
	    Toast.makeText(context, "当前电量为:"+percent+"%", 0).show();
	}

}
