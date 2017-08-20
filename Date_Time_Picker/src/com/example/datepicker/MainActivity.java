package com.example.datepicker;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import android.widget.Toast;

public class MainActivity extends Activity {
	DatePicker datepicker;
	
    int year,monthOfYear,dayOfMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datepicker=(DatePicker)this.findViewById(R.id.datePicker1);
        //获得系统的日期
        Calendar calendar=Calendar.getInstance();
        //初始化年月日
		year=calendar.get(Calendar.YEAR);
		monthOfYear=calendar.get(Calendar.MONTH)+1;
		dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        datepicker.init(year, monthOfYear, dayOfMonth, 
		 new OnDateChangedListener() {
	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
		String value=arg1+"年-"+(arg2+1)+"月-"+arg3+"日";
		Toast.makeText(MainActivity.this, value, 0).show();
	}
}); 
    }


    
    
}
