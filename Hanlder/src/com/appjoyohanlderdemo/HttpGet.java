package com.appjoyohanlderdemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

//访问网络的工具类
public class HttpGet {
	/**
	 * 调用请求网络文本、图片以及下载的任务时,调用者需要在子线程中运行
	 */
	private String path, downloadPath;
	private static final String IMAGE_ADDRESS = "imageFiles";
	private static final String DWONLOAD_ADDRESS = "downloadFiles";
	private static HttpGet hg = new HttpGet();
	private Bitmap bitmap = null;
	private String data = null;

	public static HttpGet getInstance() {
		return hg;
	}

	private HttpGet() {

	}

	// 创建一个放下载资源的文件夹
	public void createDownloadFile() {
		downloadPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + DWONLOAD_ADDRESS;
		File downloadFile = new File(downloadPath);
		if (!downloadFile.exists()) {
			downloadFile.mkdir();
			// System.out.println("---创建了下载文件夹");
		}
	}

	// 创建一个放缓存图片的文件夹
	public void createCacheImageFile() {
		path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/" + IMAGE_ADDRESS;
		File imageFile = new File(path);
		if (!imageFile.exists()) {
			// mkdirs()表示创建一个多级文件:/a/aaa.txt,即创建a,也创建aaa.txt.
			imageFile.mkdir();
		}
	}

	// 判断是否有缓存图片
	public boolean isMusicImage(String fileName) {
		File f = new File(path + "/" + fileName);
		return f.exists();
	}

	// 从bitmap图片工厂中取出符合名字的图片
	public Bitmap getBitmapFromfileName(String fileName) {
		return BitmapFactory.decodeFile(path + "/" + fileName);
	}

	// 将传过来的图片保存到创建的文件夹下
	public void saveBitmap(Bitmap bitmap, String fileName) {
		File f = new File(path + "/" + fileName);
		try {
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);// 100是一点不压缩
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
    //加载网络图片
	public Bitmap getImageURL(final String strUrl) {
				InputStream is= null;
				HttpURLConnection httpConn = null;
				try {
					URL url = new URL(strUrl);
					httpConn = (HttpURLConnection) url.openConnection();
					httpConn.setConnectTimeout(5000);
					is = httpConn.getInputStream();
					BufferedInputStream buff = new BufferedInputStream(is);
					bitmap = BitmapFactory.decodeStream(buff);
					// 图片保存到本地
					// saveBitmap(bitmap, fileName);
				} catch (MalformedURLException e) {
					System.out.println("图片无法显示");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("网络异常");
					e.printStackTrace();
				} finally {
					try {
						if (is != null) {
							is.close();
						}
						httpConn.disconnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		return bitmap;
	}
    //加载网络文本
	public String getURL(final String strUrl) {
				InputStream is = null;
				HttpURLConnection httpCon = null;
				try {
					// 将地址转化为URL
					URL url = new URL(strUrl);
					// 打开链接
					httpCon = (HttpURLConnection) url.openConnection();
					// 设置超时时间
					httpCon.setConnectTimeout(5000);
					// 获取字节流
					is = httpCon.getInputStream();
					// 将字节流转化为字符流
					InputStreamReader reader = new InputStreamReader(is);
					// 设置缓冲区
					BufferedReader buffere = new BufferedReader(reader);
					// 取数据
					String str;
					StringBuffer sb = new StringBuffer();
					while ((str = buffere.readLine()) != null) {
						sb.append(str);// 连接字符
					}
					data = sb.toString();
				} catch (MalformedURLException e) {
					System.out.println("网页无法显示");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("网络异常");
					e.printStackTrace();
				} finally {
					try {
						if (is != null) {
							is.close();
						}
						httpCon.disconnect();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
		return data;
	}

	// 下载歌曲到指定文件夹
	public void download(String Musicurl,String MusicName) {
				try {
					URL url = new URL(Musicurl);
					URLConnection con = url.openConnection();
					InputStream in = con.getInputStream();
					File f = new File(downloadPath + "/" + MusicName);
					if (!f.exists()) {
						f.createNewFile();
					}
					byte[] bytes = new byte[1024];
					int len;
					int length = 0;//记录下载进度总长度
					FileOutputStream out = new FileOutputStream(f);
					while ((len = in.read(bytes)) != -1) {
						length+=len;//叠加每次读取的长度
						out.write(bytes,0,len);
					}
					out.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

	}

}
