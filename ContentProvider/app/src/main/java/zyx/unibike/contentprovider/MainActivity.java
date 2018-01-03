package zyx.unibike.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zyx.unibike.sql.PetMetaData;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClick(View v){
        ContentResolver contentResolver=this.getContentResolver();
        Uri uri=Uri.parse("content://zyx.unibike.contentprovider.dogprovider/dog");
        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME, "zyx");
        values.put(PetMetaData.DogTable.AGE, 17);

        contentResolver.insert(uri, values);
    }
    public void deleteClick(View v){
        ContentResolver contentResolver=this.getContentResolver();
        Uri uri=Uri.parse("content://zyx.unibike.contentprovider.dogprovider/dog/1");

        contentResolver.delete(uri,null,null);
    }
    public void updateClick(View v){
        ContentResolver contentResolver=this.getContentResolver();
        Uri uri=Uri.parse("content://zyx.unibike.contentprovider.dogprovider/dog/1");
        ContentValues values = new ContentValues();
        values.put(PetMetaData.DogTable.NAME, "zyx");
        values.put(PetMetaData.DogTable.AGE, 22);

        contentResolver.update(uri,values,null,null);
    }
    public void queryClick(View v){
        ContentResolver contentResolver=this.getContentResolver();
        Uri uri=Uri.parse("content://zyx.unibike.contentprovider.dogprovider/dog");

        Cursor c=contentResolver.query(uri,null,null,null,null);
         while(c.moveToNext()) {
             System.out.print(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable._ID)));
             System.out.print(c.getString(c.getColumnIndexOrThrow(PetMetaData.DogTable.NAME)));
             System.out.print(c.getInt(c.getColumnIndexOrThrow(PetMetaData.DogTable.AGE)));
         }
         c.close();
    }
}
