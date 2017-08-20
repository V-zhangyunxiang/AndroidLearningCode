package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive4 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "有序广播2", 0).show();
	
		//向下一级广播传递数据
		Bundle data=new Bundle();
		data.putString("info", "广播2发出的数据");
		setResultExtras(data);
		//中断有序广播,只有有序广播才有中断
		//abortBroadcast();
	}

}
