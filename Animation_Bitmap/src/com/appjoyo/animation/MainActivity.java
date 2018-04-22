package com.appjoyo.animation;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView iv,iv2;
    AnimationDrawable animDrawble;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView) findViewById(R.id.imageView1);
		iv2=(ImageView)findViewById(R.id.imageView2);
	}

	public void anim_alpha(View v){
		 //加载动画资源文件,补间动画
//		 Animation alpha=AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
//		 Animation scale=AnimationUtils.loadAnimation(this, R.anim.scale_anim);
//		 Animation translate=AnimationUtils.loadAnimation(this, R.anim.translate_anim);
//		 Animation rotate=AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
//		 iv.startAnimation(rotate);//绑定到组件上
//       -------------------------------------------------------------------------
		//帧动画
//      ImageView一般用background引用帧动画资源文件,src引用会自动播放帧动画
//		animDrawble=(AnimationDrawable) iv.getBackground();
//		animDrawble.start();
//		animDrawble.stop();
	}
	//属性动画
	public void objectAnim(View view){
//   ObjectAnimator.ofFloat(iv2, "rotationX", 0f,360f).setDuration(300).start();
    //组合多个动画在一起,这种组合方式会同时执行所有动画
//	PropertyValuesHolder pvha=PropertyValuesHolder.ofFloat("alpha", 1f,0f,1f);
//	PropertyValuesHolder pvhb=PropertyValuesHolder.ofFloat("scaleX", 1f,0f,1f);	
//	PropertyValuesHolder pvhc=PropertyValuesHolder.ofFloat("scaleY", 1f,0f,1f);	
//	ObjectAnimator.ofPropertyValuesHolder(iv2,pvha,pvhb,pvhc).setDuration(500).start();
//  -----------------------------------------------------------------------------------	    
		//自由落体运动
//		final View v=view;
//		DisplayMetrics dm=new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//      //ValueAnimator.ofFloat()参数可扩展,与object不同的是:不事先决定用什么动画和用在哪个组件上
//	    ValueAnimator va=ValueAnimator.ofFloat(view.getY(),dm.heightPixels).setDuration(1000);
//		va.addUpdateListener(new AnimatorUpdateListener() {
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				//设置具体怎么动,并加载到组件上
//				v.setTranslationY((Float) animation.getAnimatedValue());
//			}
//		});
//		va.start();
//-----------------------------------------------------------------------------------------
//		final View v=view;
//		//监听动画事件
//		ObjectAnimator oa=ObjectAnimator.ofFloat(view,"alpha",1f,0f).setDuration(1000);
//		//动画监听器,监听所有过程
//		oa.addListener(new AnimatorListener() {
//			@Override
//			public void onAnimationStart(Animator animation) {
//			}
//			@Override
//			public void onAnimationRepeat(Animator animation) {
//			}
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				ViewGroup viewGroup=(ViewGroup)v.getParent();
//				if(viewGroup!=null){
//					viewGroup.removeView(v);
//				}
//			}
//			@Override
//			public void onAnimationCancel(Animator animation) {
//			}
//		});
		//用适配器,只监听我们需要的方法
//		oa.addListener(new AnimatorListenerAdapter() {
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				
//				super.onAnimationEnd(animation);
//			}
//		});
//		oa.start();
//------------------------------------------
//  AnimatorSet的使用
	ObjectAnimator oa1=ObjectAnimator.ofFloat(view,"rotationX", 0f,360f);
	ObjectAnimator oa2=ObjectAnimator.ofFloat(view,"alpha",1f,0f);
	ObjectAnimator oa3=ObjectAnimator.ofFloat(view,"scaleX",1f,0f);
    AnimatorSet set=new AnimatorSet();
    set.setDuration(1000);
    set.playTogether(oa1,oa2,oa3);//同时执行
    set.setStartDelay(300);//延迟执行
    set.playSequentially(oa1,oa2,oa3);//顺序执行
    //灵活设置先后前后顺序,不能超过2个方法的连接
//  set.play(oa1).with(oa2);
//  set.play(oa3).after(oa2);
//  set.setInterpolator(new BounceInterpolator());//设置插值器,统一设置没效果,需要单独设置插值器
    set.start();
	}
}