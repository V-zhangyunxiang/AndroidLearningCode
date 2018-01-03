package com.example.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class NLNotification extends Activity{
	Button btn_normal,btn_large;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.normal_large_notification);
		btn_normal=(Button) findViewById(R.id.button_normal);
		//btn_large=(Button) findViewById(R.id.button_large);
		//1.发送一个普通通知
		btn_normal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//API 11之前的通知方式
//		    Notification n=new Notification();
		    //API 11之后的通知方式
//		    Notification.Builder builder=new Notification.Builder(NLNotification.this);
		    //v4兼容包的通知方式
		    NotificationCompat.Builder builder=new NotificationCompat.Builder(NLNotification.this);
			//设置小图标
		    builder.setSmallIcon(R.drawable.ic_launcher);
		    //表示下拉后的图标,不设置时用未下拉时的小的
//		    Bitmap bitmap =BitmapFactory.decodeResource(getResources(),
//			R.drawable.ic_category_0);
//			builder.setLargeIcon(bitmap);
		    builder.setAutoCancel(true);//是否点击后自动删除
			builder.setContentTitle("你有一条新消息");
			builder.setContentText("新年快乐,万事如意");
			builder.setTicker("普通视图新消息");//未被下拉时所显示的消息提示
//			builder.setOngoing(true);//是否可以被删除,当为true时，不能被清除
			//设置来通知时,用声音,振动,呼吸灯去提示用户,ALL表示3个同时启动
			builder.setDefaults(Notification.DEFAULT_ALL);
			//表示发送几条通知
			builder.setNumber(10);
			//设置优先级 -2 ~ 2 默认为0
             mBuilder.setPriority(2);
//         //设置点击后是否自动消失
            mBuilder.setAutoCancel(true);
			//点击通知跳转通知详情页
			Intent intent=new Intent(NLNotification.this,NotificationReceive.class);
			//设置通知详情页的入栈方式
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putExtra("msg", "新年快乐,万事如意");//可以给通知详情页传递信息
			//参数:上下文对象,请求码,跳到那个页面,创建PendingIntent的方式
			PendingIntent pi=PendingIntent.getActivity(NLNotification.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			//通知的点击事件
			builder.setContentIntent(pi);
			
			//获取系统的通知管理器
			NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			//发送通知
			//0表示通知的唯一标识符,builder.build()创建一个通知对象
			//同一个id,通知内容是可以改的
			nm.notify(0, builder.build());
			}
		});
	}
	//2.发送一个大视图通知
	public void sendLargeNotifi(View v){
		 NotificationCompat.Builder builder=new NotificationCompat.Builder(this);	
		 builder.setSmallIcon(R.drawable.ic_launcher);
		 builder.setTicker("大视图新消息");
		 NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle();
	     //设置大视图标题
		 style.setBigContentTitle("我是大视图");
		 //设置概括性文本
		 style.setSummaryText("这是张云翔写的诗");
		 //为大视图添加一行一行内容
		 style.addLine("长亭外");
		 style.addLine("古道边");
		 style.addLine("小桥流水");
		 //把内容添加到通知里
		 builder.setStyle(style);
		//获取系统的通知管理器
		NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//发送通知
		//0表示通知的唯一标识符,builder.build()创建一个通知对象
		//同一个id,通知内容是可以改的
		nm.notify(0, builder.build());
		//点击事件同普通视图一样
	}
	//3.发送一个进度条通知
	public void sendProgressNotifi(View v){
		 final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);	
		 builder.setSmallIcon(R.drawable.ic_launcher);
		 builder.setTicker("进度条新消息");
		 builder.setContentTitle("下载中....");
		 builder.setContentText("正在下载王者荣耀...");
		 final NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		 //nm.notify(0, builder.build());
		 new Thread(){
		 @Override
		 public void run() {
	     for (int i = 0; i<=100; i+=5) {
	         builder.setProgress(100, i, false);
	         nm.notify(0, builder.build());
	         try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	        builder.setProgress(0, 0, false);
		    builder.setContentText("更新完成");
		    nm.notify(0, builder.build());
			}
		 }.start();
		//点击事件同普通视图一样
	}
	//4.发送一个自定义通知.多用于音乐播放器,放在服务里显示通知,并通过广播处理控件点击事件
	public void sendDiyNotifi(View v){
     NotificationCompat.Builder builder=new NotificationCompat.Builder(this);		
     builder.setSmallIcon(R.drawable.ic_launcher);
	 builder.setTicker("自定义播放器");
	 builder.setOngoing(true);
	 //远程视图设置自定义通知
	 RemoteViews rv=new RemoteViews(getPackageName(), R.layout.item);
	 //设置视图里的控件属性
	 rv.setTextViewText(R.id.textView1, "西海情歌");
	 /*
	  * 设置视图里的某个控件的单击事件
	  * viewId 哪个控件   pendingIntent
	  */
	 //rv.setOnClickPendingIntent(viewId, pendingIntent);
	 //把远程视图放到通知里
	 builder.setContent(rv);
	 NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	 nm.notify(0, builder.build());
	}

}
