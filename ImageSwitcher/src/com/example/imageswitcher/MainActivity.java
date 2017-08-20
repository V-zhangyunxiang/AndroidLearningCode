package com.example.imageswitcher;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
   private ImageSwitcher imageSwitcher;//同样适用TextSwitcher
   private int[]images={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
   private int index;//下标
   float startX=0.0f;
   float endX=0.0f;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageSwitcher1);
        imageSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView iv=new ImageView(MainActivity.this);
				iv.setImageResource(images[0]);
				return iv;
			}
		});
        imageSwitcher.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action=event.getAction();
				if(action==MotionEvent.ACTION_DOWN){//开始滑
					startX=event.getX();
					//return true;
				}
				if(action==MotionEvent.ACTION_UP){//离开屏幕
					endX=event.getX();
					if(startX-endX>20){//向左滑
						index=index+1<images.length?++index:0;
						imageSwitcher.setInAnimation(MainActivity.this, android.R.anim.fade_in);
						imageSwitcher.setOutAnimation(MainActivity.this, android.R.anim.fade_out);
						imageSwitcher.setImageResource(images[index]);
					}else if(endX-startX>20){//向右滑
						index=index-1>=0?--index:images.length-1;
						imageSwitcher.setInAnimation(MainActivity.this, android.R.anim.fade_in);
						imageSwitcher.setOutAnimation(MainActivity.this, android.R.anim.fade_out);
						imageSwitcher.setImageResource(images[index]);
						
					}
				}
				return true;
			}
		});
    }
  


  
    
}
