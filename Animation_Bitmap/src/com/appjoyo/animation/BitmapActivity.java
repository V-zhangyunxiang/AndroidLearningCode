package com.appjoyo.animation;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

public class BitmapActivity extends Activity{
	private ImageView iv;
	private LruCache<String, Bitmap> lruCache; 
	/**
	 * BitmapFactory类提供了几种解码方式(decodeByteArray,decodeFile,decodeResource)
	 * 根据资源选择解码方式,这些方法请求分配内存来构造位图,很容易导致oom(内存溢出)异常.
	 * -解决方法:
	 * 1.BitmapFactory.Options指定解码选项
	 * 2.设置inJustDecodeBounds属性为true(允许在创建位图之前去读取图像的尺寸)
	 * 3.通过计算采样比例缩小图片.
	 * 之前我们常用软/弱引用来缓存图片,但是现在的安卓版本垃圾回收器更加注重于对于这两种引用的回收,所以现在这种缓存方法基本无效.
	 *  最新的解决方法:
	 *  1.LruCache(内存缓存)  LRU:least recently used 近期最少使用算法
	 *  -将对象保存在一个LinkedHashMap的强引用中,缓存超过指定大小之前释放最近很少使用的对象的内存
	 *  2.diskLruCache(磁盘缓存)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bitmap_activity);
		iv=(ImageView) findViewById(R.id.imageView1);
		//获取当前Activity的内存大小
		ActivityManager am=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		int memoryClass=am.getMemoryClass();
		final int cacheSize=memoryClass/8*1024*1024;// 1/8作为缓存大小(常用)
		
		lruCache=new LruCache<String, Bitmap>(cacheSize);
	}
	//添加缓存
	public void addBitmapToCache(String key,Bitmap bitmap){
		if(getBitmapFromCaches(key)==null){
			lruCache.put(key, bitmap);
		}
	}
	//得到缓存
	public Bitmap getBitmapFromCaches(String key){
		return lruCache.get(key);
		
	}
	public void bitmapLoading(View view){
		String key=String.valueOf(R.drawable.picasso);//最好是Url
		Bitmap bitmap=getBitmapFromCaches(key);
		if(bitmap==null){
		  bitmap=decodeSampledBitmapFromResource(getResources(),R.drawable.picasso, 150, 150);
		  addBitmapToCache(key, bitmap);
		}else{
			//2.从磁盘获取  3.网络加载
		}
		iv.setImageBitmap(bitmap);
	}
	/**
	 * 
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 * 位图重新采样(压缩)
	 */
	public Bitmap decodeSampledBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;               //先得到边框尺寸
		BitmapFactory.decodeResource(res, resId,options);
		options.inSampleSize=calculateInsampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(res, resId,options);
	}
	/**
	 * 计算位图的采样比例
	 * reqWidth reqHeight 图片需要的宽高
	 */
	public int calculateInsampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
		int w=options.outWidth;
		int h=options.outHeight;              //真实宽高
		int inSampleSize=1;
		if(w>reqWidth || h>reqHeight){
			if(w>h){                 //取图片宽高中的最小值作为比例,避免失真
				inSampleSize=Math.round((float)h/(float)reqHeight);
			}else{
				inSampleSize=Math.round((float)w/(float)reqWidth);
			}
		}
		System.out.println("size:"+inSampleSize);
		return inSampleSize;
		
	}
	
}
