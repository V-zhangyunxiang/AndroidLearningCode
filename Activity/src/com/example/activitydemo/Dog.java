package com.example.activitydemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Dog implements Parcelable {
	private String name;
	private int age;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(type);
	}

	@Override
	public String toString() {

		return name + age + type;
	}
    //对象的创建器
	public static final Parcelable.Creator<Dog> CREATOR = 
			new Parcelable.Creator<Dog>() {
		public Dog createFromParcel(Parcel in) {
			Dog dog=new Dog();
			//与写入时的顺序要一一对应
			dog.name=in.readString();
			dog.age=in.readInt();
			dog.type=in.readString();
			return dog;
		}

		public Dog[] newArray(int size) {
			return new Dog[size];
		}
	};

}
