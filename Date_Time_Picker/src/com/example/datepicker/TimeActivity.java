package com.example.datepicker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class TimeActivity extends Activity {
	TimePicker timepicker;
	AnalogClock analogclock;
	DigitalClock digitalclock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_main);
		timepicker = (TimePicker) this.findViewById(R.id.timePicker1);
		timepicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				String value = hourOfDay + "时" + minute + "分";
				Toast.makeText(TimeActivity.this, value, 0).show();
			}
		});
		analogclock = (AnalogClock) this.findViewById(R.id.analogClock1);
		digitalclock = (DigitalClock) this.findViewById(R.id.digitalClock1);
	}

}
