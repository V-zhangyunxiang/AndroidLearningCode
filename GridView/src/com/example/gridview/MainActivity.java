package com.example.gridview;



import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//自定义适配器，在getview()中设置图片的显示方式,达到图文混排效果

public class MainActivity extends Activity {
    GridView text_gridview;  
    ImageAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_gridview=(GridView)findViewById(R.id.text_gridview);
        adapter=new ImageAdapter();//可以有参数，参数可以为一个函数
        text_gridview.setAdapter(adapter);
        text_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
		//view就是所在xml文件的根视图的布局，通过id找到所要找的view组件（如textview）
		Toast.makeText(MainActivity.this,"-->>"+arg2,0).show();
				
			}
        	
		});
     
    }
 int []images={R.drawable.ic_category_0,
		       R.drawable.ic_category_1,
		       R.drawable.ic_category_2,
               R.drawable.ic_category_3,
               R.drawable.ic_category_4,
               R.drawable.ic_category_5,
               R.drawable.ic_category_7,
               R.drawable.ic_category_13};
 String []names={"美食","旅行","步行","酒店","外卖","飞机","火车","大船"};
     public  class ImageAdapter extends BaseAdapter{

    	  public ImageAdapter(){
    		  
    	  }
		@Override
		public int getCount() {
			
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			
			return images[position];//(names[position]也可以)
		}

		@Override
		public long getItemId(int position) {
		
			return position;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			//该方法会被多次调用
			//手工创建View对象,生成视图（单个视图常用）
			//图片和文字就生成一个XML文件来引用（多个视图常用）
			
			//这个为单个图片
//		    ImageView iv=null;
//			if(arg1==null){
//				iv=new ImageView(MainActivity.this);
//			}
//			else{
//				iv=(ImageView)arg1;
//			}
//			
//			iv.setImageResource(images[position]);
//			return iv;
			
			
		//图文混排
			
	    //第一种方法	
//		ViewHolder vh;
//		if(arg1==null){        
//			arg1=getLayoutInflater().inflate(R.layout.items, null);
//			vh=new ViewHolder();
//			vh.iv_icon=(ImageView)arg1.findViewById(R.id.image);
//			vh.tv_icon=(TextView)arg1.findViewById(R.id.title);
//			arg1.setTag(vh);
//			}
//		else{
//				vh=(ViewHolder)arg1.getTag();
//			}
//			vh.iv_icon.setImageResource(images[position]);
//			vh.tv_icon.setText(names[position]);
//			return arg1;
			
			//第二种方法
			View view=null;
			if(arg1==null){
			   view=getLayoutInflater().inflate(R.layout.items, null);
			}
			else{
				view=arg1;
			}
			
			ImageView iv=(ImageView)view.findViewById(R.id.image);
			TextView tv=(TextView)view.findViewById(R.id.title);
			iv.setImageResource(images[position]);
			tv.setText(names[position]);
			return view;
		}
 //用于保存第一次查找的组件，避免下次重复查找
// class ViewHolder{            //第一种方法的优化函数
//			ImageView iv_icon;
//			TextView tv_icon;
		//}
     }

  
    
}
