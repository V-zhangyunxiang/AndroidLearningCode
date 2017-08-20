package com.zyx.camera_media;

import java.io.IOException;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class VitamioActivity extends Activity implements
		MediaPlayer.OnPreparedListener {
	private MediaPlayer mp;
	 //Vitamio框架播放音频
	/**
	 * Vitamio项目导入:
	 * 1.Eclipse:将VitamioBundle-master中的vitamio包导入到Eclipse中,会自动生成一个initActivity项目
	 *   将其作为Library,主项目导入这个library,并且将VitamioBundle-master/vitamio-sample中的raw文件夹拷贝到主项目中
	 *   在清单文件中加入initActivity的特殊声明,并加入相应权限
	 * 2.Android Studio:在主项目的根目录中新建一个文件夹,将VitamioBundle-master中的vitamio包复制过来,在AS中将该文件夹作为modle library
	 *   注意build gradle中的版本号要一致,并且label和icon的属性可能报错,可以直接删除这两个属性
	 *   在清单文件中加入initActivity的特殊声明,并加入相应权限
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载so类库,通常使用C/C++编写的代码
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.acticity_vitamio);
		if (mp == null) {
			mp = new MediaPlayer(this);
		}
	}

	public void play(View view) {
		String path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
				+ "/1486383589673.mp3";
		try {
			mp.reset();
			mp.setDataSource(path);
			mp.setOnPreparedListener(this);
			mp.prepareAsync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause(View view) {
		mp.pause();
	}

	public void stop(View view) {
		mp.stop();
		mp.release();
	}
   //接口回调
	@Override
	public void onPrepared(MediaPlayer arg0) {
		arg0.start();
	}

}
