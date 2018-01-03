package com.appjoyo.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyBindService extends Service {
	 /**
     * 绑定服务是进程间的通信(IPC),只有Activity,Service,ContentProvider才能绑定服务,广播不能绑定
     * Android接口定义语言=AIDL:线程不安全的,支持并发处理     代理设计模式
     * 1.客户端(Activity等)通过bindService方法来绑定一个(通常意义上的)业务服务对象(接口)
     *   成功后,会回调onServiceConnected方法
     * 2.客户端通过ServiceConnection组件来暴露业务接口
     * 3.服务端创建一个**.aidl文件来定义一个可以被客户端调用的业务接口
     * aidl文件:
     *   (1)不能有修饰符,类似接口的写法
     *   (2)支持的类型:基本数据类型,String,List,Map,自定义类型
     *   自定义类型:
     *    1.实现Parcelable接口
     *    2.定义一个aidl文件申明该类型  parcelable Person;
     *    3.在其他aidl文件中使用,需要使用import导入
     * 4.服务端需要提供一个实现业务接口的实现类,继承Stub类
     * 5.通过Service的onBind()返回被绑定的业务对象
     * 6.客户端可以调用远程的业务对象方法
     */
	@Override
	public IBinder onBind(Intent intent) {

		return new CatImpl();
	}

	@Override
	public void onCreate() {

		super.onCreate();
	}
	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		
		return super.onUnbind(intent);
	}
}
