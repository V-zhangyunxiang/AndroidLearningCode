package zyx.unibike.loaders;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import zyx.unibike.sql.DataBaseAdapter;
import zyx.unibike.sql.Dog;
import zyx.unibike.sql.PetMetaData;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private DataBaseAdapter dbAdapter;
    private CursorLoader loader;
    private SimpleCursorAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter=new DataBaseAdapter(this);
        ListView lv = (ListView) findViewById(R.id.listView);
        cursorAdapter=new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                dbAdapter.list(),
                new String[]{
                         PetMetaData.DogTable._ID,
                         PetMetaData.DogTable.NAME,
                         PetMetaData.DogTable.AGE,
                          },
                new int[]{R.id.textView,R.id.textView2,R.id.textView3},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(cursorAdapter);
        //初始化一个loaders(id,bundle参数,回调接口)
        getLoaderManager().initLoader(0,null,this);
    }

    public void addClick(View v){
          dbAdapter.add(new Dog("zyx",45));
          //重启加载器
          //getLoaderManager().restartLoader(0,null,this);
          //api18以上可用
          //loader.commitContentChanged();
          loader.onContentChanged();//内容发生了改变,通知加载器
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri=Uri.parse("content://zyx.unibike.loaders.dogprovider/dog");
        //创建一个游标加载器(上下文,cp的URI,要查询的列,查询条件,查询条件的值,排序条件)
        loader=new CursorLoader(this,uri,null,null,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
