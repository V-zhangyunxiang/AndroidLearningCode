/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\AndroidSpace\\ServiceDemo\\src\\com\\appjoyo\\servicedemo\\ICat.aidl
 */
package com.appjoyo.servicedemo;
public interface ICat extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.appjoyo.servicedemo.ICat
{
private static final java.lang.String DESCRIPTOR = "com.appjoyo.servicedemo.ICat";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.appjoyo.servicedemo.ICat interface,
 * generating a proxy if needed.
 */
public static com.appjoyo.servicedemo.ICat asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.appjoyo.servicedemo.ICat))) {
return ((com.appjoyo.servicedemo.ICat)iin);
}
return new com.appjoyo.servicedemo.ICat.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getPerson:
{
data.enforceInterface(DESCRIPTOR);
com.appjoyo.servicedemo.Person _result = this.getPerson();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_setName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.setName(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_desc:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.desc();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_basicTypes:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
long _arg1;
_arg1 = data.readLong();
boolean _arg2;
_arg2 = (0!=data.readInt());
float _arg3;
_arg3 = data.readFloat();
double _arg4;
_arg4 = data.readDouble();
java.lang.String _arg5;
_arg5 = data.readString();
this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.appjoyo.servicedemo.ICat
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.appjoyo.servicedemo.Person getPerson() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.appjoyo.servicedemo.Person _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPerson, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.appjoyo.servicedemo.Person.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void setName(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_setName, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String desc() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_desc, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void basicTypes(int anInt, long along, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(anInt);
_data.writeLong(along);
_data.writeInt(((aBoolean)?(1):(0)));
_data.writeFloat(aFloat);
_data.writeDouble(aDouble);
_data.writeString(aString);
mRemote.transact(Stub.TRANSACTION_basicTypes, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getPerson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_desc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public com.appjoyo.servicedemo.Person getPerson() throws android.os.RemoteException;
public void setName(java.lang.String name) throws android.os.RemoteException;
public java.lang.String desc() throws android.os.RemoteException;
public void basicTypes(int anInt, long along, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;
}
