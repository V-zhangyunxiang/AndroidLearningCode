package com.example.listview2;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
  ListView listView;
  MyAdapter adapter;
   
  ////自定义适配器，在getview()中设置图片的显示方式,达到滚动列表效果
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
      listView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Toast.makeText(MainActivity.this,"-->>"+arg2,0).show();
			
		}
	});
        
    }
   int []images={R.drawable.ic_category_0,R.drawable.ic_category_1,R.drawable.ic_category_2,
   R.drawable.ic_category_3,R.drawable.ic_category_4,R.drawable.ic_category_5,R.drawable.ic_category_7,R.drawable.ic_category_13};
    public class MyAdapter extends BaseAdapter{
 
    	public MyAdapter(){  //接收new MyAdapter()所传过来的参数
    		
    	}
		@Override
		public int getCount() {
			
			return images.length;
		}

		@Override
		public Object getItem(int arg0) {
		
			return images[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView imageView=null;
			if(arg1==null){
				imageView=new ImageView(MainActivity.this);
			}else{
				imageView=(ImageView)arg1;
			}
		
			
			imageView.setImageResource(images[arg0]);
			
			return imageView;
		}
    	
    }


    
    
}
