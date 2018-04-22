package com.zyx.camera_media;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Audio;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class SurfaceVideoActivity extends Activity implements SurfaceHolder.Callback {
	private SurfaceView surfaceView;
	private SurfaceHolder holder;
	private MediaPlayer mp;
/**
 *  SurfaceView播放视频
 * .9图片一般是png格式,用sdk/tool里的draw9path命令制作
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_surface);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		holder = surfaceView.getHolder();
		holder.addCallback(this);
        holder.setFixedSize(320, 220);//设置视频分辨率
	}

	public void playClick(View view) {
		mp.start();

	}

	public void pauseClick(View view) {
		mp.pause();

	}

	public void stopClick(View view) {
		mp.stop();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mp = new MediaPlayer();
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.setDisplay(holder);
		String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/1486381268617.mp4";
		try {
		  
			mp.reset();//重置
			mp.setDataSource(path);//设置播放源
			mp.prepare();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.stop();
				mp.release();  //释放
			}

		}
	}

	@Override
	protected void onDestroy() {
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.stop();
				mp.release();
			}

		}
		super.onDestroy();
	}

}
