package com.xiaozhu.glidedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

//自定义变换
public class MyTransform extends BitmapTransformation {


    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//        int diameter = Math.min(toTransform.getWidth(), toTransform.getHeight());
//
//        final Bitmap toReuse = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
//        final Bitmap result;
//        if (toReuse != null) {
//            result = toReuse;
//        } else {
//            result = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
//        }
//
//        int dx = (toTransform.getWidth() - diameter) / 2;
//        int dy = (toTransform.getHeight() - diameter) / 2;
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP,
//                BitmapShader.TileMode.CLAMP);
//        if (dx != 0 || dy != 0) {
//            Matrix matrix = new Matrix();
//            matrix.setTranslate(-dx, -dy);
//            shader.setLocalMatrix(matrix);
//        }
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        float radius = diameter / 2f;
//        canvas.drawCircle(radius, radius, radius, paint);
//
//        if (toReuse != null && !pool.put(toReuse)) {
//            toReuse.recycle();
//        }
//        return result;
        return null;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }


    public MyTransform(Context context) {
        super(context);
    }

}
