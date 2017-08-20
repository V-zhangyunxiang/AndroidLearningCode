package com.appjoyohanlderdemo;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;

	/**
	 * Handler实现机制:1.每一个应用程序都会创建一个MessageQueue
	 *              2.handler对象负责把消息(+对象)push到消息队列中,并接收looper从消息队列中取出的消息(+对象)
	 *              3.MessageQueue对象,存放消息对象的消息队列,先进先出原则
	 *              4.looper管理当前线程的消息队列
	 *                Message:内部使用链表数据结构实现一个消息池,避免大面积创建对象,造成内存浪费 
	 * handler内存泄漏分析:
	 * 1.定义一个内部类时,会默认拥有外部类对象的引用,所以建议使用内部类时,定义为静态内部类(静态不持有外部类对象的引用)
	 * 2.引用的强弱:强引用->软引用->弱引用->虚引用
	 * 软引用与弱引用的区别:
	 * 1.软引用:如果内存空间足够,垃圾回收器就不会回收它,如果内存空间不足了,就会回收这些对象的内存。
	 * 2.弱引用:只具有弱引用的对象拥有更短暂的生命周期,在垃圾回收器线程扫描它所管辖的内存区域的过程中
	 *        一旦发现了只具有弱引用的对象,不管当前内存空间足够与否,都会回收它的内存。
	 * handler的闪屏加载:
	 *     一般用于欢迎页面  handler.postDelay(new Runable(),3000);
	   接口回调机制也能更新UI
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
	}
    //该写法为标准的handler写法,避免了内存泄漏问题
	private MyHandler handler=new MyHandler(this);//传递外部类对象
	private static class MyHandler extends Handler {
		WeakReference<MainActivity> weakReference;
        //activity指代外部类的对象
		public MyHandler(MainActivity activity) {
			weakReference = new WeakReference<MainActivity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			MainActivity activity = weakReference.get();//得到外部类对象
			if (activity != null) {
				switch (msg.what) {
				case 100:
					activity.tv.setText("下载完成");
					break;

				default:
					break;
				}
			}
			super.handleMessage(msg);
		}
	}

	public void downloadClick(View v) {
		new Thread(new Runnable() {
			int i = 0;
			boolean flag = true;
			public void run() {
				while (flag) {
					i++;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == 10) {
						handler.sendEmptyMessage(100);
						// Message msg=Message.obtain();
						// msg.arg1=1;
						// msg.obj=flag;
						// handler.sendEmptyMessageDelayed(100, 500);
						// handler.sendMessage(msg);
						flag = false;
					}
				}
			}
		}).start();

		// new Thread() {
		// @Override
		// public void run() {
		//
		// super.run();
		// }
		// }.start();
	}

}
