package com.example.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MantivityResult extends Activity implements OnClickListener {
	EditText ed;
	Button button1, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		ed = (EditText) findViewById(R.id.editText1);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 选择号码
			Intent intent = new Intent(this, PhoneNumberActivity.class);
			startActivityForResult(intent, 001);
			break;
		case R.id.button2:
			// 打电话
			String number = ed.getText().toString();
			Intent intent2 = new Intent();
			intent2.setAction(Intent.ACTION_DIAL);// 或者Intent.ACTION_CALL
			intent2.setData(Uri.parse("tel:" + number));//tel不能少
			startActivity(intent2);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 001 && resultCode == 002) {
			ed.setText(data.getStringExtra("number"));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
