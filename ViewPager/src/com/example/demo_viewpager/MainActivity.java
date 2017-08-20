package com.example.demo_viewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private List<ImageView> mDots;//定义一个集合存储三个dot
	private int oldPosition;//记录当前点的位置。
	ArrayList<View> datas; 
	private int msgwhat;
	ViewPager vp;
    ImageView iv1,iv2,iv3;
    Handler hanlder=new Handler(){         //匿名内部类的写法
		@Override
		public void handleMessage(Message msg) {
			if(vp.getCurrentItem()+1==mDots.size()){
				vp.setCurrentItem(0);
			}else{
			    vp.setCurrentItem(vp.getCurrentItem()+1);
			}
			hanlder.sendEmptyMessageDelayed(msgwhat, 2000);//2s后发送,保持了循环
			super.handleMessage(msg);
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vp=(ViewPager) findViewById(R.id.vp);
		datas=new ArrayList<View>();//实例化集合对象
		View view1=LayoutInflater.from(this).inflate(R.layout.vp1, null);
		View view2=LayoutInflater.from(this).inflate(R.layout.vp2, null);
		View view3=LayoutInflater.from(this).inflate(R.layout.vp3, null);
		datas.add(view1);
		datas.add(view2);
		datas.add(view3);
		mDots=new ArrayList<ImageView>();//实例化三个点
        iv1=(ImageView) findViewById(R.id.iv1);
        iv2=(ImageView) findViewById(R.id.iv2);
        iv3=(ImageView) findViewById(R.id.iv3);
        mDots.add(iv1);
        mDots.add(iv2);
        mDots.add(iv3);
        //默认第一个点被选中
        mDots.get(oldPosition).setImageResource(R.drawable.page_now);
		vp.setAdapter(adapter);	
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				//当前页面
				//System.out.println("--onPageSelected"+arg0);
				mDots.get(oldPosition).setImageResource(R.drawable.page);
				mDots.get(arg0).setImageResource(R.drawable.page_now);
				oldPosition=arg0;
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//滑动时执行
				//System.out.println("--onPageScrolled"+arg0+arg1+arg2);	
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			//arg0 =1 开始滑动 ;=2惯性滑动 ;=0结束滑动
			//System.out.println("--onPageScrollStateChanged"+arg0);	
			}
		});
		
	}
	protected void onResume() {
		super.onResume();//这句话必须有
		hanlder.sendEmptyMessageDelayed(msgwhat, 2000);
		
	};
	protected void onStop() {
		super.onStop();
		hanlder.removeMessages(msgwhat);
		
	};
	PagerAdapter adapter=new PagerAdapter() {
		
		//返回boolean,是否需要加载
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		//得到数据长度
		public int getCount() {
			return datas.size();
		}
		//移出划出屏幕的view
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(datas.get(position));
		}
		//加载滑入屏幕的view,左右两边都加载,关闭除左右的.
		public Object instantiateItem(ViewGroup container, int position) {
			View view=datas.get(position);//得到view对象
			container.addView(view);//装载到容器
			return view;
		}
		
	};
	
}
