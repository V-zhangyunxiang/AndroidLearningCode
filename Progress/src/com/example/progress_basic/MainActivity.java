package com.example.progress_basic;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	SeekBar seekbar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 标题栏进度条
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_main);
		setProgressBarIndeterminateVisibility(true);
		seekbar = (SeekBar) this.findViewById(R.id.seekBar1);
		seekbar.setMax(100);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				System.out.println("--->" + progress);
			}
		});
	}
	public void showpro(View v) {
		ProgressDialog pd = new ProgressDialog(this);
		pd.setMax(100);
		pd.setIndeterminate(false);//是否是不确定的
		pd.setProgress(30);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setCancelable(true);//是否可以被取消
		pd.setTitle("下载对话框");
		pd.setMessage("正在下载中...");
		pd.show();
		// ProgressDialog pd=ProgressDialog.show(this, "doload",
		// "doloading",false,true);
	}

}
