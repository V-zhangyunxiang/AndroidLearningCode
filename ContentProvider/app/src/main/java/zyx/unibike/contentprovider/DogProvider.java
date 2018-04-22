package zyx.unibike.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import zyx.unibike.sql.DatabaseHelper;
import zyx.unibike.sql.PetMetaData;

/**
 * Created by VULCAN on 2017/7/30.
 */

public class DogProvider extends ContentProvider {

    private static final String AUTHORITIES = "zyx.unibike.contentprovider.dogprovider";
    private static final int SINGLE_CODE = 1;  //返回的匹配码
    private static final int MUTIPLE_CODE= 2;

    private static final String SINGLE_TYPE="vnd.android.cursor.item/dog";
    private static final String MUTIPLE_TYPE="vnd.android.cursor.dir/dog";

    //创建一个URI的匹配器
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        //content://zyx.unibike.contentprovider.helloprovider/dog
        uriMatcher.addURI(AUTHORITIES, "dog", MUTIPLE_CODE);
        //content://zyx.unibike.contentprovider.helloprovider/dog/1
         //#表示数字通配符，可表示任意数字
        uriMatcher.addURI(AUTHORITIES, "dog/#", SINGLE_CODE);
    }

    private DatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PetMetaData.DogTable._ID + "=?";
                selectionArgs = new String[]{String.valueOf(id)};
                return db.query(PetMetaData.DogTable.TABLE_NAME, projection, selection,selectionArgs,null,null,sortOrder);
            case MUTIPLE_CODE:
                db = dbHelper.getWritableDatabase();
                return db.query(PetMetaData.DogTable.TABLE_NAME, projection, selection,selectionArgs,null,null,sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case SINGLE_CODE:
                return SINGLE_TYPE;
            case MUTIPLE_CODE:
                return MUTIPLE_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)){
            //只判断查询多个的uri，因为id是自增的，不能由人自定义id。
            case MUTIPLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id= db.insert(PetMetaData.DogTable.TABLE_NAME,null,values);
                uri=ContentUris.withAppendedId(uri, id);
                db.close();
                break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id=ContentUris.parseId(uri);
                selection=PetMetaData.DogTable._ID+"=?";
                selectionArgs=new String[]{String.valueOf(id)};
                int row=  db.delete(PetMetaData.DogTable.TABLE_NAME,selection,selectionArgs);
                db.close();
                return row;
            case MUTIPLE_CODE:
                db = dbHelper.getWritableDatabase();
                row=  db.delete(PetMetaData.DogTable.TABLE_NAME,selection,selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id=ContentUris.parseId(uri);
                selection=PetMetaData.DogTable._ID+"=?";
                selectionArgs=new String[]{String.valueOf(id)};
                int row=  db.update(PetMetaData.DogTable.TABLE_NAME,values,selection,selectionArgs);
                db.close();
                return row;
            case MUTIPLE_CODE:
                db = dbHelper.getWritableDatabase();
                row=  db.update(PetMetaData.DogTable.TABLE_NAME,values,selection,selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }
}
