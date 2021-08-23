package com.xiaozhu.glidedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        imageView = findViewById(R.id.icon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://guolin.tech/test.gif";
                RequestOptions options = new RequestOptions()
                        //占位图
                        .placeholder(R.mipmap.a)
                        .error(R.drawable.ic_launcher_background)
                        //Target.SIZE_ORIGINAL 原图大小
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        //.override(500,400)

                        //禁用内存缓存,默认开启
                        //.skipMemoryCache(true)

                        //图片变换
                        //.transform(new MyTransform(this))
                        // 禁用图片变换
                        //.dontTransform()
                        //centerCrop .centerInside() .circleCrop() .fitCenter() 自带几种变换，不需要使用 transform()
                        .circleCrop()
                        //设置图片质量 ，默认 ARGB_8888
                        .format(DecodeFormat.DEFAULT)
                        /* DiskCacheStrategy.NONE： 表示不缓存任何内容。
                         DiskCacheStrategy.DATA： 表示只缓存原始图片。
                         DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
                         DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
                         DiskCacheStrategy.AUTOMATIC：表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项*/
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                // with load into 简单三步
                Glide.with(MainActivity.this)
                        //asBitmap asFile asDrawable
                        //强制使用静态图片
                        .asBitmap()
                        .load(url)
                        .apply(options)
                        //preload() 预加载，替代 into，还要去掉 apply
                        //listener 可结合 into/preload 使用
                        .listener(new RequestListener<Bitmap>() {
                            //返回 false 就表示这个事件没有被处理，还会继续向下传递，返回 true 就表示这个事件已经被处理掉了，从而不会再继续向下传递
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(imageView);
                imageView.setVisibility(View.VISIBLE);
                //downloadImage();
            }
        });
    }

    // 自定义 into 方法  into(simpleTarget)：表示加载自定义 into 方法  viewTarget(不限制 View 类型)
    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            //可以将 drawable 二次处理
            imageView.setImageDrawable(resource);
        }
    };


    // submit() 显示图片路径，代替 into
    public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    //获取下载的图片文件
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
