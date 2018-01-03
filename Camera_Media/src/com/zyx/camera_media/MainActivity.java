package com.zyx.camera_media;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
    private MediaRecorder mr;//录音对象
    private boolean isPrepare=false;
    private Button startAudio,stopAudio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startAudio=(Button) findViewById(R.id.button3);
		stopAudio=(Button) findViewById(R.id.button4);
	}
	//开始录音
	public void audioStartClick(View view){
		if(mr==null){
		   mr=new MediaRecorder();
		}
		mr.reset();
		mr.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音源
		mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//设置音频格式
		mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		String path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/"+System.currentTimeMillis()+".mp3";
		mr.setOutputFile(path);
		try {
			mr.prepare();
			isPrepare=true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    if(isPrepare){
		 mr.start();
		 isPrepare=false;
		 startAudio.setEnabled(false);
		 stopAudio.setEnabled(true);
		}
	}
	 //停止录音
	 public void audioStopClick(View view){
			mr.stop();
			startAudio.setEnabled(true);
			stopAudio.setEnabled(false);
		}
		//释放媒体资源
		@Override
		protected void onDestroy() {
			if(mr!=null){
				mr.release();
			}
			super.onDestroy();
		}
//------------------------------------------------------------		
		
	// 调用照相机
	public void cameraClick(View view) {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,      //EXTRA_OUTPUT 保存指定路径
				getOutputMediaFileUri(MEDIA_TYPE_IMAGE));
		startActivityForResult(intent, MEDIA_TYPE_IMAGE);
	}
   
	// 录视频
	public void videoClick(View view) {
        Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputMediaFileUri(MEDIA_TYPE_VIDEO));
	    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//1表示视频质量高
	    startActivityForResult(intent, MEDIA_TYPE_VIDEO);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MEDIA_TYPE_IMAGE) {
			Uri uri = data.getData();
			System.out.println("uri:" + uri);
		} else if(requestCode == MEDIA_TYPE_VIDEO){
			Uri uri = data.getData();
            System.out.println("uri:"+uri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public Uri getOutputMediaFileUri(int type) {
		File file = getOutputMediaFile(type);
		return Uri.fromFile(file);
	}

	public File getOutputMediaFile(int type) {
		File file = null;
		String rootPath = null;
		switch (type) {
		case MEDIA_TYPE_IMAGE:
			rootPath = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES).getPath();
			file = new File(rootPath + File.separator
					+ System.currentTimeMillis() + ".jpg");
			break;
		case MEDIA_TYPE_VIDEO:
			rootPath = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_MOVIES).getPath();
			file = new File(rootPath + File.separator  //File.separator 分隔符
					+ System.currentTimeMillis() + ".mp4");
			break;
		default:
			break;
		}
		return file;
	}

}
