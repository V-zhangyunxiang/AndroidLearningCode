package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartModelB extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmodel_b);
	}

	public void startA(View v) {
		Intent intent = new Intent(this, StartModelA.class);
		startActivity(intent);

	}

	public void startB(View v) {
		Intent intent = new Intent(this, StartModelB.class);
		startActivity(intent);

	}

}
