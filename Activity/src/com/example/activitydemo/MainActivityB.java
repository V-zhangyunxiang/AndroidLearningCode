package com.example.activitydemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivityB extends Activity {
	
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        tv=(TextView) findViewById(R.id.textView1);
        //1.第一种取值
//        String msg=getIntent().getStringExtra("msg");
        //2.第二种取值
//        Bundle b=getIntent().getBundleExtra("bundle");
//        tv.setText(msg);
//        System.out.println("bundle:"+ b.getString("code"));
        //取得序列化对象的属性值
//        System.out.println("cat:"+getIntent().getSerializableExtra("cat").toString());
          System.out.println("dog"+getIntent().getParcelableExtra("dog").toString());
    
    }


  
    
}
