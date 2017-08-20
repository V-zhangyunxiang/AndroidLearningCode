package com.example.spinner_basic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
  AutoCompleteTextView autoCompleteTextView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    autoCompleteTextView1=(AutoCompleteTextView)this.findViewById(R.id.autoCompleteTextView1);
    //加载xml文件用creatfromResource
    ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.city_name,
    android.R.layout.simple_dropdown_item_1line);
    autoCompleteTextView1.setAdapter(adapter);
    }


  
    
}
