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


//�Զ�������������getview()������ͼƬ����ʾ��ʽ,�ﵽͼ�Ļ���Ч��

public class MainActivity extends Activity {
    GridView text_gridview;  
    ImageAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_gridview=(GridView)findViewById(R.id.text_gridview);
        adapter=new ImageAdapter();//�����в�������������Ϊһ������
        text_gridview.setAdapter(adapter);
        text_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
		//view��������xml�ļ��ĸ���ͼ�Ĳ��֣�ͨ��id�ҵ���Ҫ�ҵ�view�������textview��
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
 String []names={"��ʳ","����","����","�Ƶ�","����","�ɻ�","��","��"};
     public  class ImageAdapter extends BaseAdapter{

    	  public ImageAdapter(){
    		  
    	  }
		@Override
		public int getCount() {
			
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			
			return images[position];//(names[position]Ҳ����)
		}

		@Override
		public long getItemId(int position) {
		
			return position;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			//�÷����ᱻ��ε���
			//�ֹ�����View����,������ͼ��������ͼ���ã�
			//ͼƬ�����־�����һ��XML�ļ������ã������ͼ���ã�
			
			//���Ϊ����ͼƬ
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
			
			
		//ͼ�Ļ���
			
	    //��һ�ַ���	
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
			
			//�ڶ��ַ���
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
 //���ڱ����һ�β��ҵ�����������´��ظ�����
// class ViewHolder{            //��һ�ַ������Ż�����
//			ImageView iv_icon;
//			TextView tv_icon;
		//}
     }

  
    
}
