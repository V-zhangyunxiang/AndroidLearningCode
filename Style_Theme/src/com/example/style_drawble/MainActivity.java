package com.example.style_drawble;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.ClipDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView iv;
	ClipDrawable clip;
/*
 *样式是作用于某个个体View的,在Style.xml里设置
 *主题是作用于整个Activity或者应用程序的,在Style.xml里设置,在AndroidManifest.xml里应用
 *小范围覆盖大范围的样式
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.imageView11);
		// ImageView为src时, 得到图片getDrawable()
		// 如果为background, 得到图片getBackground()
		clip = (ClipDrawable) iv.getDrawable();
		// 设置取值范围,0~10000
		clip.setLevel(5000);
		//获取Asset资源管理器
		AssetManager manager = getAssets();
		try {
			//通过manager获取资源描述
			//原始资源放在raw和assets中,raw可以直接用R文件引用
			//raw不能有子目录(被R文件编译),assets可以有子目录(不会被R文件编译)
			AssetFileDescriptor des = manager.openFd("mp3/tkfrom.mp3");
			MediaPlayer player = new MediaPlayer();
			player.reset();
			player.setDataSource(des.getFileDescriptor());
			player.prepare();
			player.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}

}
