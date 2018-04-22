package com.example.spinner;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ArrayAdapter adapter;
	Spinner spinner;
	TextView tv1,tv2;
    View v1,v2;
    String []str;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.array);
		spinner = (Spinner) findViewById(R.id.spinner);
		v1=findViewById(R.id.ic1);
		v2=findViewById(R.id.ic2);
		//两个include包含同一个布局，用view查找对象
		tv1=(TextView) v1.findViewById(R.id.textView1);
		tv2=(TextView) v2.findViewById(R.id.textView1);
		//数组资源
//		str=getResources().getStringArray(R.array.city_name);
//		for(String s:str){
//			System.out.println(s);
//		}
		//1.手工构建数据
		String[] name = { "张三", "李四", "王五"};
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_dropdown_item, name);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
                               //parent.getItemAtPosition(position)
				String item = spinner.getItemAtPosition(position).toString();
				Toast.makeText(MainActivity.this, "你选中了" + item, 0).show();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		// 2.加载xml文件
		// adapter =ArrayAdapter.createFromResource(this, R.array.city_name,
		// android.R.layout.simple_spinner_item);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spinner.setAdapter(adapter);
	}
}
