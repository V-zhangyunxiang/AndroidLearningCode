package com.unibike.ndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 一般应用是用不到NDK的,但涉及到硬件不得不用NDK了,另外C/C++效率比较高,耗时操作可以放到NDK
 * NDK:Native Development Kit,是一个工具集,集成了Android的交叉编译环境
 * 提供一套MakeFile,帮助开发者快速开发C/C++的动态库,自动将So和Java程序打包成apk,在Android上运行
 *
 * JNI:Java Native Interface(Java本地调用),允许Java代码和其他语言写的代码进行交互
 * JNI程序实现步骤:
 *  1.编写带有Native声明的方法的Java类
 *  2.使用Javac编译所写的Java类    --开发工具自动完成
 *  3.生成扩展名为.h的头文件(把native方法生成为.h文件)
 *
 *    用cmd切换到native方法所在类的Java包路径，调用javah -jni 包名路径+类名 生成.h文件
 *
 *  4 使用C/C++实现本地方法(Android.mk)   .mk文件里面的 :与=之间不能有空格
 *
 *  5.使用C/C++编写的文件生成动态连接库(jni目录下 ndk-build)
 *
 *   Android Studio中:local.properties文件中添加ndk路径
 *                   build gradle中添加 ndk{moduleName "module名称"}
 *
 *
 *  6.测试
 *  **/
public class MainActivity extends AppCompatActivity {
    private EditText ed1,ed2;
    private TextView tv;
    //加载动态连接库
    static{
            System.loadLibrary("hello");
	      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.editText);
        ed2=(EditText) findViewById(R.id.editText2);
        tv= (TextView) findViewById(R.id.textView);
    }

    public native int add(int num1, int num2);

    public void addClick(View view) {
        String value1=ed1.getText().toString().trim();
        String value2=ed2.getText().toString().trim();
        //调用本地方法
        int result=add(Integer.parseInt(value1), Integer.parseInt(value2));
        tv.setText(result);

    }

}
