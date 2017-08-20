package com.appjoyo.servicedemo;



import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
    String name;
    String work;
    @Override
    public String toString() {
    	
    	return name+work;
    }
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(work);
	}
	 //对象的创建器
		public static final Parcelable.Creator<Person> CREATOR = 
				new Parcelable.Creator<Person>() {
			public Person createFromParcel(Parcel in) {
				Person person=new Person();
				//与写入时的顺序要一一对应
				person.name=in.readString();
				person.work=in.readString();
				return person;
			}

			public Person[] newArray(int size) {
				return new Person[size];
			}
		};
    
}
