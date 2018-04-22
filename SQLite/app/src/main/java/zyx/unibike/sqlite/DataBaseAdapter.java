package zyx.unibike.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by VULCAN on 2017/7/29.
 */

public class DataBaseAdapter {

    private DatabaseHelper dbHelper;

    //也可以用原生sql语句进行增删改查，db.rawQuery()，rawDelete().....

    public DataBaseAdapter (Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    //使用事务，事务具有原子性，可同时执行多条语句，要么一起成功，一条失败就都失败
    public void transaction(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("insert into dog(name,age)values('duang',4)");
            db.execSQL("insert into dog(name,age)values('dang',2)");
            db.setTransactionSuccessful();//设置事务的成功标记
        }finally {
            db.endTransaction();//结束事务。判断事务标记是否为true，如果为true，就提交事务，否则回滚。
        }
        db.close();
    }

    public void add(Dog dog) {
       //获取操作数据库的工具类
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME, dog.getName());
        values.put(PetMetaData.DogTable.AGE, dog.getAge());
         // 表名，可以为空的列名，ContentValues
         // 第二个参数是保证values为空时，设置某一列可以为空，以保证插入语句的合法性
        db.insert(PetMetaData.DogTable.TABLE_NAME,null,values);
        db.close();
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause=PetMetaData.DogTable._ID+"=?";
        String []whereArgs={String.valueOf(id)};
        //表名, 条件，条件的值
        db.delete(PetMetaData.DogTable.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }

    public void update(Dog dog) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME, dog.getName());
        values.put(PetMetaData.DogTable.AGE, dog.getAge());
        String whereClause=PetMetaData.DogTable._ID+"=?";
        String []whereArgs={String.valueOf(dog.getId())};
        //表名，ContentValues，条件，条件的值
        db.update(PetMetaData.DogTable.TABLE_NAME, values,whereClause,whereArgs);
        db.close();
    }

    public Dog findById(int id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String []columns={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //是否去除重复项，表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页条件
        Cursor c= db.query(true,PetMetaData.DogTable.TABLE_NAME,columns,PetMetaData.DogTable._ID,new String[]{String.valueOf(id)},null,null,null,null);
        Dog dog=null;
        while(c.moveToNext()){
            dog=new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
        }
        c.close();
        db.close();
        return dog;
    }

    public ArrayList<Dog> finaAll() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String []columns={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        //是否去除重复项，表名，要查询的列，查询条件，查询条件的值，分组条件，分组条件的值，排序，分页条件
        Cursor c= db.query(true,PetMetaData.DogTable.TABLE_NAME,columns,null,null,null,null,null,null);

        ArrayList<Dog> dogs=new ArrayList<>();
        Dog dog=null;
        while(c.moveToNext()){
            dog=new Dog();
            dog.setId(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
            dog.setName(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
            dog.setAge(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        c.close();
        db.close();
        return dogs;
    }
}
