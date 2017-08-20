package com.zyx.three_party;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PullRefreshActivity extends Activity implements OnRefreshListener2<ListView>{
	private PullToRefreshListView listview;
	private ArrayList<Music> list = new ArrayList<Music>();
    private DataAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pull_refresh);
		listview = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
		//设置监听事件
		listview.setOnRefreshListener(this);
		//设置上拉下拉权限
		listview.setMode(PullToRefreshBase.Mode.BOTH);
		//自定义设置刷新时的文本
		 ILoadingLayout topLayout=listview.getLoadingLayoutProxy(true, false);
	     topLayout.setPullLabel("下拉刷新...");
	     topLayout.setReleaseLabel("释放立即刷新");
	     topLayout.setRefreshingLabel("正在刷新...");
//	     topLayout.setLoadingDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//		 topLayout.setLastUpdatedLabel("刷新成功");
	     ILoadingLayout footLayout=listview.getLoadingLayoutProxy(false, true);
	     footLayout.setPullLabel("查看更多...");
	     footLayout.setReleaseLabel("释放加载更多");
	     footLayout.setRefreshingLabel("正在加载...");
		 loadData();
		 adapter=new DataAdapter(this, list);
		 listview.setAdapter(adapter);
	
	
	}

	private int count = 1;

	// 模拟数据
	public void loadData() {
		for (int i = 0; i < 10; i++) {
			list.add(new Music("歌曲" + count, "歌手" + count));
			count++;
		}
	}
	//模拟加载数据
	static class loadDataAsyncTask extends AsyncTask<Void, Void, String>{
		private PullRefreshActivity acticity;
		public loadDataAsyncTask(PullRefreshActivity acticity){
			this.acticity=acticity;
		}

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			acticity.loadData();
			return "success";
		}

		@Override
		protected void onPostExecute(String result) {
			if(result.equals("success")){
				acticity.adapter.notifyDataSetChanged();
				acticity.listview.onRefreshComplete();//完成刷新
			}
			super.onPostExecute(result);
		}
		
	}

	// 适配器
	static class DataAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<Music> list;

		private DataAdapter(Context context, ArrayList<Music> list) {
			this.context = context;
			this.list = list;
		}

		@Override
		public int getCount() {

			return list.size();
		}

		@Override
		public Object getItem(int position) {

			return list.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView==null){
            	convertView=LayoutInflater.from(context).inflate(R.layout.ptr_item, null);
                vh=new ViewHolder();
                vh.tv_title=(TextView) convertView.findViewById(R.id.textView1);
                vh.tv_artist=(TextView) convertView.findViewById(R.id.textView2);
                convertView.setTag(vh);
            }else{
            	vh=(ViewHolder) convertView.getTag();
            }
            vh.tv_title.setText(list.get(position).getTitle());
            vh.tv_artist.setText(list.get(position).getArtist());
			return convertView;
		}

	}
	static class ViewHolder{
		TextView tv_title;
		TextView tv_artist;
	}
	//下拉刷新
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		new loadDataAsyncTask(this).execute();
		
	}
	//上拉加载更多
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		new loadDataAsyncTask(this).execute();
	}

}
