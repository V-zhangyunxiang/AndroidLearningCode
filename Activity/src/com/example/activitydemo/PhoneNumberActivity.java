package com.example.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhoneNumberActivity extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phonenumber);
		listView = (ListView) findViewById(R.id.listView1);
		final String[] numbers = { "13839764197", "15670310756" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1,
				android.R.id.text1, numbers);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String number = numbers[position];
				Intent intent = new Intent();
				intent.putExtra("number", number);
				setResult(002, intent);
				finish();
			}
		});
	}

	

}
