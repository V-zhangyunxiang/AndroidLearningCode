package com.example.filemanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void writePrivateFile(View v) {
		try {
			/*
			 * Context.MODE_PRIVATE 表示当文件名相同时,覆盖该文件 
			 * Context.MODE_APPEND  表示当文件名相同时,添加内容
			 */
			FileOutputStream out = openFileOutput("zyx.txt",
					Context.MODE_PRIVATE);
			String info = "我是中国人";
			out.write(info.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public void readPrivateFile(View v) {
		try {
			FileInputStream in = openFileInput("zyx.txt");
			byte bytes[] = new byte[1024];
			int num;
			//StringBuffer方法有synchronized锁，所以线程安全,适用于多线程,效率低
			//Stringbuilder方法用char[]数组,append没有同步锁,适用于单线程,效率高
			StringBuffer sb=new StringBuffer();
			while ((num = in.read(bytes)) != -1) {
				sb.append(new String(bytes,0,num));	
			}
			in.close();
			Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void readRawFile(View v){
		 InputStream in=getResources().openRawResource(R.raw.zyx);
		 byte bytes[]=new byte[1024];
		 StringBuilder sb=new StringBuilder();
		 int num;
		 try {
			while((num=in.read(bytes))!=-1){
				 sb.append(new String (bytes,0,num));
			 }
			Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//写入到SD卡中的私有文件中
	public void writeSdCard(View v){
		  File file=getExternalFilesDir(null);
		  if(file!=null){
			  try {
				FileOutputStream out=new FileOutputStream(file+"/dd.txt");
			    out.write("你听说过安利吗".getBytes());
			    out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		  }
		
	   }
	public void writePrivateCacheFile(View v){
		//写入到SD卡的私有缓存文件中
		//File temp=File.createTempFile("temp", null, getExternalCacheDirs());
		try {
			//写入到缓存文件中
			File temp=File.createTempFile("temp", null, getCacheDir());
			FileOutputStream out=new FileOutputStream(temp);
			out.write("我时长为海贼王航的男人".getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//判断是否有SD卡,操作SD卡需要获得权限!
	public void isSdCard(View v){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			Toast.makeText(this, "有SD卡", Toast.LENGTH_SHORT).show();
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
				Toast.makeText(this, "SD卡只读", Toast.LENGTH_SHORT).show();
			}else{
				//SD卡路径
				System.out.println(Environment.getExternalStorageDirectory().getPath());
				//返回android内置的特定类型文件夹
				System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
			}
		}else{
			Toast.makeText(this, "未装备SD卡", Toast.LENGTH_SHORT).show();
		}
	}
	
}
