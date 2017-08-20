package com.zyx.three_party;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UILActivity extends Activity{
	/**
	 * 图片缓存组件 Universal-Image-Loader
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String url="http://www.baidu.com/img/bd_logo1.png";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uil);
		ImageView image=(ImageView) findViewById(R.id.imageView1);
		
		DisplayImageOptions options=new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片质量,RGB_565节省内存,ARGB_8888原图显示
		.showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnLoading(R.drawable.ic_launcher)//默认图片
		.showImageOnFail(R.drawable.ic_launcher)//加载失败显示的图片  
//		.displayer(new RoundedBitmapDisplayer(20))//设置圆角
        .displayer(new BitmapDisplayer() {
			@Override
			public void display(Bitmap arg0, ImageAware arg1, LoadedFrom arg2) {
			Bitmap bitmap1=Bitmap.createBitmap(arg0.getWidth(),arg0.getHeight(),Bitmap.Config.ARGB_8888);
			Canvas canvas=new Canvas(bitmap1);
			BitmapShader shader=new BitmapShader(arg0, TileMode.CLAMP, TileMode.CLAMP);
			Paint paint=new Paint();
			paint.setShader(shader);
			canvas.drawCircle(arg0.getWidth()/2, arg0.getHeight()/2, arg0.getHeight()/2, paint);
			arg1.setImageBitmap(bitmap1);
			}
		})
		.build();
		
		ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
		.memoryCacheSizePercentage(20)//设置占用内存的百分比
		.diskCacheFileCount(100) //可以缓存的文件数量 
		.diskCacheSize(5*1024*1024)//sd卡(本地)缓存的最大值  
		.defaultDisplayImageOptions(options)//设置加载的属性
		.build();
		ImageLoader.getInstance().init(configuration);//初始化配置
		//可以添加不同的options参数,来满足对图片形状的不同需求
		ImageLoader.getInstance().displayImage(url, image);
		
		
		//下载图片.可以实现预加载
//		ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
//			
//			@Override
//			public void onLoadingStarted(String arg0, View arg1) {
//			}
//			
//			@Override
//			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//			}
//			
//			@Override
//			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//			}
//			
//			@Override
//			public void onLoadingCancelled(String arg0, View arg1) {
//			}
//		});
		//加载图片时的监听事件
//		ImageLoader.getInstance().displayImage(url, image,new ImageLoadingListener() {
//			
//			@Override
//			public void onLoadingStarted(String arg0, View arg1) {
//			}
//			
//			@Override
//			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//			}
//			
//			@Override
//			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//			}
//			
//			@Override
//			public void onLoadingCancelled(String arg0, View arg1) {
//			}
//		},new ImageLoadingProgressListener() {
//			
//			@Override
//			public void onProgressUpdate(String arg0, View arg1, int arg2, int arg3) {
//			}
//		});
	}

}
