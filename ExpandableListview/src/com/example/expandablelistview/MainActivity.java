package com.example.expandablelistview;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
/**
 * 可扩展的listview  只支持两级展开
 * 如果有3级以上，建议用页面跳转的方式打开
 */
  private ExpandableListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ExpandableListView) findViewById(R.id.expandableListView1);
        MyExpandableAdapter ma=new MyExpandableAdapter();
        listView.setAdapter(ma);
        listView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(MainActivity.this, childs[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
				return true;
			}
		});
    }
	 private String[]groups={"好友","扣丁学堂"};
     private String[][]childs={{"阿哥","阿妈"},{"威哥","伟哥"}};
    
 class MyExpandableAdapter extends BaseExpandableListAdapter{

	@Override
	public int getGroupCount() {
		
		return groups.length;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		
		return childs[groupPosition].length;
	}
	@Override
	public Object getGroup(int groupPosition) {
		
		return groups[groupPosition];
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		
		return childs[groupPosition][childPosition];
	}
	@Override
	public long getGroupId(int groupPosition) {
		
		return groupPosition;
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		
		return childPosition;
	}
	@Override
	public boolean hasStableIds() {
	
		return false;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=getLayoutInflater().inflate(R.layout.group_layout, null);
		}
		ImageView im=(ImageView) convertView.findViewById(R.id.icon);
		TextView tv=(TextView) convertView.findViewById(R.id.title);
	    tv.setText(groups[groupPosition]);
	    
		return convertView;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=getLayoutInflater().inflate(R.layout.child_layout, null);
		}
		ImageView im=(ImageView) convertView.findViewById(R.id.icon);
		TextView tv=(TextView) convertView.findViewById(R.id.title);
	    tv.setText(childs[groupPosition][childPosition]);
		return convertView;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		
		return true;
	}
	
		
		
     }

   
}
