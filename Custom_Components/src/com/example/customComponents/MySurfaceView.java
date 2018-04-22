package com.example.customComponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * SurfaceView是独立于主UI线程的,刷新非常快,用于游戏和视频频繁刷新屏幕所用,属于自定义View
 * 该例子是手动去画的,也可以用XML布局定义SurfaceView
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder holder;
	private MyThread mThread;
	public MySurfaceView(Context context) {
		super(context);
		holder=this.getHolder();
		holder.addCallback(this);
		mThread=new MyThread(holder);
	}
       static class MyThread implements Runnable{
        private SurfaceHolder holder;
		public boolean isRun;
		public MyThread(SurfaceHolder holder){
			this.holder=holder;
			isRun=true;
		}
        @Override
		public void run() {
        	int count=0;
        	Canvas canvas=null;
        	while(isRun){
        		try {
        		synchronized (holder) {
					canvas=holder.lockCanvas();
					Paint p=new Paint();
					p.setColor(Color.RED);
					p.setStyle(Paint.Style.FILL);//实心画笔
					p.setAntiAlias(true);//去锯齿
					p.setTextSize(30);
					canvas.drawRect(10,10,100,100,p);//矩形,数字为坐标,屏幕左上方为原点
					canvas.drawText("当前是:"+(count++)+"秒", 10, 150, p);
					Thread.sleep(1000);
        		    }
				}catch (InterruptedException e) {
						e.printStackTrace();
				}finally{
					if(canvas!=null)
					holder.unlockCanvasAndPost(canvas);
				}
				}
        	}
		}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.isRun=true;
	    new Thread(mThread).start();
		}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThread.isRun=false;
	}

}
