package com.example.imageswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ViewFlipper;

public class ViewFlipperDemo extends Activity {
	private ViewFlipper viewflipper;
	private float startX=0.0f;
	private float endX=0.0f;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
		viewflipper=(ViewFlipper)findViewById(R.id.viewflipper);
//		viewflipper.setOnTouchListener(new OnTouchListener() {
//			两种响应触摸事件的方法都可以使用
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				
//				return false;
//			}
//		});
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action=event.getAction();
		if(action==MotionEvent.ACTION_DOWN){
			startX=event.getX();
		}
		if(action==MotionEvent.ACTION_UP){
			endX=event.getX();
			if(startX>endX){
				viewflipper.setInAnimation(this,android.R.anim.fade_in);
				viewflipper.setOutAnimation(this, android.R.anim.fade_out);
				viewflipper.showNext();
			}else if(startX<endX){
				viewflipper.setInAnimation(this,android.R.anim.fade_in);
				viewflipper.setOutAnimation(this, android.R.anim.fade_out);
				viewflipper.showPrevious();
			}
		}
		return super.onTouchEvent(event);
	}

}
