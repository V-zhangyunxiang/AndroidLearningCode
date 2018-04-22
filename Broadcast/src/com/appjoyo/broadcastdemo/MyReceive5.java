package com.appjoyo.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceive5 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
	    
	Toast.makeText(context, "粘性广播已接收", 0).show();
	
	}

}
