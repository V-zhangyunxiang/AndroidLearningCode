package com.appjoyo.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {

	public static final int SAY_HELLO = 0x1;

	public MessengerService() {

	}

	@Override
	public IBinder onBind(Intent intent) {

		return messenger.getBinder();
	}

	private Handler hanlder = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SAY_HELLO:
				String info = (String) msg.obj;
				Toast.makeText(MessengerService.this, info, 1).show();
				break;

			default:
				break;
			}

		};

	};
	private Messenger messenger = new Messenger(hanlder);

}
