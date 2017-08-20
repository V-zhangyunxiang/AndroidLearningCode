package com.example.notificationdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NotificationReceive extends Activity {
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nr_receive);
		tv=(TextView) findViewById(R.id.tv_recrive);
	    String msg=	getIntent().getStringExtra("msg");
	    tv.setText(msg);
	    //也可以在通知详情页取消通知
//        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    //取消哪一条通知
//	      nm.cancel(0);
	    //取消所有通知
//	      nm.cancelAll();
	}
	

}
