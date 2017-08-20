package com.example.customComponents;

import com.example.customComponents.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{
	private int textColor;
	private float textSize;
	private String text;
	private Paint  paint;//画笔
    //AttributeSet:属性集合  attrs是我们的xml文件
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
		//获取配置文件中的属性值
		TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.MyView);
		/* R.styleable.MyView_textColor:表示得到哪个属性
		 * 第二个参数表示如果取不到时的默认值
		 */
		textColor= array.getColor(R.styleable.MyView_textColor, 0xFFFFFF);
		textSize=array.getDimension(R.styleable.MyView_textSize, 25);
		text=array.getString(R.styleable.MyView_text);
	  //array.getDrawable(R.drawable.ic_launcher);//图片的获取方式
	   
	    array.recycle();//关闭资源
	}
	//视图的绘制 Canvas:画布  Paint:画笔
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		 paint.setColor(textColor);
		 paint.setTextSize(textSize);
//       将描述的属性画到text上 ;数字表示文字的坐标(x,y)(会覆盖该组件在xml中的位置)
//		 System.out.println("text:"+text);
//		 System.out.println("canvas:"+canvas);
//		 System.out.println("paint:"+paint);
		 canvas.drawText(text, 50, 50, paint);//画文字,还可以画图片
	}


}
