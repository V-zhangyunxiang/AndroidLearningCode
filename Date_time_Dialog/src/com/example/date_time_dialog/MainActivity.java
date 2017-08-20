package com.example.date_time_dialog;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button1,button2;
    EditText edittext1;
    DatePickerDialog dialog;//日期对话框
    TimePickerDialog dialog2;//时间对话框
    int year,monthOfYear,dayOfMonth,hourOfDay,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        button1=(Button)this.findViewById(R.id.button1);
        button2=(Button)this.findViewById(R.id.button2);
        edittext1=(EditText)this.findViewById(R.id.editText1);
        
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        monthOfYear=calendar.get(Calendar.MONTH)+1;
        dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
		
        hourOfDay=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        
        dialog=new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				String value=year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
				edittext1.setText(value);
			}
		}, year, monthOfYear, dayOfMonth);
		
        button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();
			}
		});
		
        dialog2=new TimePickerDialog(this, new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	    Toast.makeText(MainActivity.this,hourOfDay+"小时"+minute+"秒" , 0).show();
			}
		}, hourOfDay, minute, true);
		
        button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog2.show();
			}
		});
    }
}
