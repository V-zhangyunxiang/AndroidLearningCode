package com.sensorsdata.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.onChildClickListener {

    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                }
                //返回 1 表示占几列或几行（根据布局方向确定）
                return 1;
            }
        });
        //需要是不规则的图表才能看到效果
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //默认是透明动画，这里使用向右移出的动画
        MyItemAnimator animator = new MyItemAnimator();
        //执行移除动画时间间隔
        //animator.setRemoveDuration(3000);
        //添加自定义动画
        recyclerView.setItemAnimator(animator);
        //该句是解决当Item视图中有图片和文字，当更新文字并调用notifyItemChanged()时，文字改变的同时图片会闪一下的问题
        //((MyItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(linearLayoutManager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("这是第" + i + "个数据");
        }
        //list.add(null);
        myAdapter = new MyAdapter(this, list);

        //装饰模式 head 和 foot 的写法
       /* MyAdapterWrapper newAdapter = new MyAdapterWrapper(myAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, recyclerView, false);
        newAdapter.addFooterView(footerView);
        newAdapter.addHeaderView(headerView);*/

        myAdapter.setOnChildClickLister(this);
        recyclerView.setAdapter(myAdapter);
        //添加自定义分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchCallback(myAdapter, list));
        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        myAdapter.remove(position);
    }

}
