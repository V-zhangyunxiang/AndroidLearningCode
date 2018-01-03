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
 *  3.使用 javah -jni 包名路径+类名(native 所在的类) 生成.h头文件 （需要 cmd 切换到该类目录）
 *  4 使用C/C++实现本地方法  在 jni 目录下编写.c/.c++文件，并把.h 文件放到该目录下
 *  5.使用C/C++编写的文件生成动态连接库(.mk文件里面的 : 与 = 之间不能有空格)
 *  6.jni目录下执行 ndk-build 生成 so 文件
    配置：Android Studio中:local.properties文件中添加ndk路径
 *       build gradle中添加 ndk{
                              moduleName "module名称（如hello）"  
                              abiFilters 'x86', 'x86_64', 'armeabi', 'armeabi-v7a','arm64-     	                      v8a'
                              }
 *  7.测试   native 所在类中加载动态连接库
            static{
                  System.loadLibrary("hello");
	          }
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
