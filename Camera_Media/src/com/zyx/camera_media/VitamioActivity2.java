package com.zyx.camera_media;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class VitamioActivity2 extends Activity{
	//Vitamio框架播放视频
	private VideoView vv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.activity_vitamio2);
		vv=(VideoView) findViewById(R.id.video_View);
		MediaController mc=new MediaController(this);
		vv.setMediaController(mc);
		//路径既可以是本地,也可以是来自网络
		String path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/1486381268617.mp4";
//		String path="http://v.ku6.com/show/izkg2xigbTKVe2ZF5K3j4g...html?from=my";
		vv.setVideoPath(path);
		vv.requestFocus();
		vv.start();
	}

}
