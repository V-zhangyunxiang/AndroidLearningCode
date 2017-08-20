package com.appjoyo.broadcastdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity5 extends Activity {
	MyReceive5 receiver5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main5);

	}

	@Override
	protected void onResume() {
		receiver5 = new MyReceive5();
		IntentFilter filter5 = new IntentFilter();
		filter5.addAction("com.appjoyo.action.MY_ACTION5");
		registerReceiver(receiver5, filter5);
		super.onResume();
	}

	@Override
	protected void onStop() {
		unregisterReceiver(receiver5);
		super.onStop();
	}

}
