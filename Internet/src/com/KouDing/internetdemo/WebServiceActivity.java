package com.KouDing.internetdemo;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class WebServiceActivity extends Activity {
	/**
	 * WebService是基于一种soap协议的远程调用标准 
	 * 通过WebService可以将不同操作系统平台,不同语言,不同技术整合到一起
	 * 
	 * 该例子为查询手机号码归属地
	 */
	// 命名空间
	String nameSpace = "http://WebXml.com.cn/";
	// 调用的方法名称
	String methodName = "getMobileCodeInfo";
	// endPoint
	String endPoint = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
	// SOAP action
	String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_service);
	}

	public void webServiceClick(View v) {

		new Thread(new Runnable() {
			@Override
			public void run() {
			//指定WebService的命名空间和方法名
			SoapObject rpc = new SoapObject(nameSpace, methodName);
		   //设置调用WebService接口需传入的两个参数
			rpc.addProperty("mobileCode", "15670310756");
			rpc.addProperty("userId", "");
			//生成调用webService方法的Soap请求信息,并指定Soap的版本
			SoapSerializationEnvelope envelop=new SoapSerializationEnvelope(SoapEnvelope.VER12);
			envelop.bodyOut=rpc;
			envelop.dotNet=true;//设置是否调用的是Net开发的WebService
			HttpTransportSE transport=new HttpTransportSE(endPoint);
			try {
				transport.call(soapAction, envelop);//调用WebService
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			//获取返回的数据
			SoapObject object=(SoapObject) envelop.bodyIn;
			String result=object.getProperty(0).toString();
			System.out.println(result);
			}
		}).start();

	}

}
