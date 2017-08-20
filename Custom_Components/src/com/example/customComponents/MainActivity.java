package com.example.customComponents;

import com.example.customComponents.R;

import android.os.Bundle;
import android.app.Activity;


public class MainActivity extends Activity {
	/**
	 * 自定义组件(view)的三种方式:
	 * 1.组合现有Android默认提供的组件:继承ViewGroup或其子layout等布局进行组合
	 * 2.调整现有Android默认提供的组件:继承View的子类具体类
	 * 3.完全自定义组件:继承View基类,界面及事件完全由自己控制
	 * 
	 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
//       setContentView(new MySurfaceView(this));
    }


   
    
}
