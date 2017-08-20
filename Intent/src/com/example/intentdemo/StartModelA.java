package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartModelA extends Activity {
	/**
	 * Activity的四种启动模式:
	 *   1.standard:构造一个新的Activity,放到目标task中的Activity栈顶
	 *   2.singleTop:判断新的Activity是否在栈顶,在的话就不再构造并放到栈顶,不在的话还是会构造并放到栈顶
	 *   3.singleTask:把该Activity上面的Activity全部清除,直到该Activity在栈顶,只允许一个Activity的一个实例(还可以加载其它Activity实例)
	 *   4.singleInstance:创建一个新的Task,只能存在一个Activity实例(不允许加载其它Activity实例),已存在就切换到该任务栈
	 *   既可以在清单文件里设置,也可以在Intent setFlags代码中设置
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmodel_a);
	}

	public void startA(View v) {
		Intent intent = new Intent(this, StartModelA.class);
		startActivity(intent);

	}

	public void startB(View v) {
		Intent intent = new Intent(this, StartModelB.class);
		startActivity(intent);

	}

}
