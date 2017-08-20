package com.appjoyohanlderdemo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpClientConnection;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaCodec.BufferInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity {
	TextView text_async;
	ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async);
		text_async = (TextView) findViewById(R.id.text_async);
		pb = (ProgressBar) findViewById(R.id.progressBar1);

	}

	public void asyncClick(View v) {
		MyAsyncTask task = new MyAsyncTask(this);// 传递一个外部类对象进去
		task.execute();// 执行异步任务,可传递多个参数
	}

	public void downloadClick(View v) {
		downloadAsyncTask downloadTask = new downloadAsyncTask(this);
		downloadTask.execute("http://www.baidu.com/img/bd_logo1.png");
	}

	private static class downloadAsyncTask extends
			AsyncTask<String, Integer, Integer> {
		private AsyncTaskActivity activity;

		public downloadAsyncTask(AsyncTaskActivity activity) {
			this.activity = activity;// 接收外部类对象,以便能使用外部类的非静态参数
		}

		@Override
		protected Integer doInBackground(String... params) {
			String s = params[0];
			try {
				URL url = new URL(s);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			    int size=conn.getContentLength();
			    publishProgress(0,size);
			    byte []bytes=new byte[1024];
			    InputStream in=conn.getInputStream();
			    FileOutputStream out=new FileOutputStream("/sdcard/"+System.currentTimeMillis()+".png");
			    int length;
			    while( (length=in.read(bytes))!=-1){
			    	out.write(bytes, 0, length);
			    	publishProgress(1,length);
			    	out.flush();
			    }
			    out.close();
			    in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return 200;
		}

		@Override
		protected void onPreExecute() {
			activity.pb.setProgress(0);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result == 200) {
				activity.text_async.setText("下载完成");
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			switch (values[0]) {
			case 0:
				activity.pb.setMax(values[1]);
				break;
			case 1:
				activity.pb.incrementProgressBy(values[1]);
				break;
			default:
				break;
			}
			super.onProgressUpdate(values);
		}

	}

	private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
		private AsyncTaskActivity activity;

		public MyAsyncTask(AsyncTaskActivity activity) {
			this.activity = activity;// 接收外部类对象,以便能使用外部类的非静态参数
		}

		// 执行后台任务的方法,类似于线程,运行在子线程
		@Override
		protected String doInBackground(String... params) {// params接收传递过来的参数
			for (int i = 0; i < 10; i++) {
				publishProgress(i);// 调用onProgressUpdate方法更新进度
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return "success";
		}

		// 执行任务之前的事件方法,可在该方法内执行初始化工作,运行在主线程
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

		// 得到doInBackground返回的参数,并更新UI,运行在主线程
		@Override
		protected void onPostExecute(String result) {
			activity.text_async.setText(result);
			super.onPostExecute(result);
		}

		// 查看更新进度方法
		@Override
		protected void onProgressUpdate(Integer... values) {
			activity.text_async.setText("当前进度为:" + values[0]);
			super.onProgressUpdate(values);
		}

	}

}
