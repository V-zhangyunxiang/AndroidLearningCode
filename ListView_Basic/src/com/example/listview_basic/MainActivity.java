package com.example.listview_basic;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends /*ListActivity*/Activity {

    @Override
    //继承ListActivity不用再设置布局
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=(ListView)this.findViewById(R.id.listView1);
//       ArrayAdapter aa=
//       ArrayAdapter.createFromResource(this, 
//       R.array.name, android.R.layout.simple_list_item_1);
//       setListAdapter(aa);
        
        //单选按钮
        String []arr=getResources().getStringArray(R.array.name);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arr);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(aa);
//      
    }
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//    	super.onListItemClick(l, v, position, id);
//    	TextView tv=(TextView)v;
//    	Toast.makeText(this, tv.getText(), 0).show();
//    }


   
}
