package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive3 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "有序广播1", 0).show();
		// 接受上一级广播发出的数据
		Bundle data = getResultExtras(false);
		String info = data.getString("info");
		Toast.makeText(context, info, 0).show();
	}

}
