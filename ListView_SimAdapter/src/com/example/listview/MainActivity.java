package com.example.listview;

import java.util.ArrayList;
import java.util.HashMap;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
 ListView listView;
    //用系统自带的SimpleAdapter实现滚动列表
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	listView=(ListView)findViewById(R.id.list);
    	 ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
         int []images={R.drawable.ic_category_0,R.drawable.ic_category_1,R.drawable.ic_category_2,
         R.drawable.ic_category_3,R.drawable.ic_category_4,R.drawable.ic_category_5,R.drawable.ic_category_7,R.drawable.ic_category_13};
        String[] text = { "a", "b", "c", "d", "e", "f", "g", "h" };
        for (int i = 0; i < 8; i++) {
     	      HashMap<String, Object> map = new HashMap<String, Object>();
     	      map.put("image", images[i]);//装到map中的图片是资源ID，而不是图片本身
     	      map.put("text", text[i]);
     	      imagelist.add(map);
     	    }
       String key[]={"image","text"};
        int value[]={R.id.image,R.id.title};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this, imagelist, R.layout.items, key, value);
        listView.setAdapter(simpleAdapter);
    	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//TextView tv=(TextView)arg1;
		switch(arg2){
		case 0:
			Toast.makeText(MainActivity.this,"-->>"+arg2 , 0).show();
			break;
		}
//				if(arg2==0){
//					Toast.makeText(MainActivity.this, "-->>"+arg2, 0).show();
//				}
				
			}
		});
				
			}
			
			
		
			


    }


 
    

