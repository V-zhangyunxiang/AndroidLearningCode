package zyx.unibike.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VULCAN on 2017/7/29.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="pet.db";  //数据库名
    private static final int VERSION=1;            //版本号
    //创建表语句
    private static final String CREATE_TABLE_DOG="CREATE TABLE dog(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT ,age INTEGER)";
    //大小写转换 ctrl+shift+U   删除表语句
    private static final String DROP_TABLE_DOG="DROP TABLE IF EXISTS dog";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    //如果表不存在，就会执行该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
             db.execSQL(CREATE_TABLE_DOG);
    }

    //升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_DOG);
        db.execSQL(CREATE_TABLE_DOG);
    }
}
