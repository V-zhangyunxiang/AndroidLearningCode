package com.appjoyo.servicedemo;

import android.os.RemoteException;

import com.appjoyo.servicedemo.ICat.Stub;

public class CatImpl extends Stub{
/**
 * 业务接口的具体实现类
 */
	private String name;

	@Override
	public void setName(String name) throws RemoteException {
		this.name=name;
	}

	@Override
	public String desc() throws RemoteException {
		
		return "hello my name is "+name;
	}

	@Override
	public void basicTypes(int anInt, long along, boolean aBoolean,
			float aFloat, double aDouble, String aString)
			throws RemoteException {
	}

	@Override
	public Person getPerson() throws RemoteException {
		Person person=new Person();
		person.name="小次良";
		person.work="强盗";
		return person;
	}

}
