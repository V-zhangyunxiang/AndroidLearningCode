package com.example.testcokeing;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

public void find(View v){
	//toast对话框文字和图片混合
	Toast t=new Toast(this);
	//文字
	TextView textview=new TextView(this);
	textview.setText("我是Toast");
	//图片
	ImageView imageview=new ImageView(this);          //可以把文字和图片用XML先布局好
	                                                  //再setView到Toast中
	imageview.setImageResource(R.drawable.ic_launcher);
	//设置属性
	LinearLayout layout=new LinearLayout(this);
	layout.setOrientation(LinearLayout.HORIZONTAL);
	layout.setGravity(Gravity.CENTER);
	//添加到布局容器中
	layout.addView(textview);
	layout.addView(imageview);

	t.setView(layout);
	t.setDuration(Toast.LENGTH_LONG);
	//t.setGravity(Gravity.CENTER, 0, 0);
	t.show();
}
    
    
    
}
