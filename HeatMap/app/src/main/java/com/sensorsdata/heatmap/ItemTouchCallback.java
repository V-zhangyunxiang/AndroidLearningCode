package com.sensorsdata.heatmap;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

public class ItemTouchCallback extends ItemTouchHelper.Callback {

    private MyAdapter mAdapter;
    private List<DataEntity.ObjectInfo> mData;
    public ItemTouchCallback(MyAdapter adapter, List<DataEntity.ObjectInfo> data){
        mAdapter = adapter;
        mData = data;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //s上下拖拽
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END; //左->右和右->左滑动
        return makeMovementFlags(dragFlag,swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(viewHolder instanceof MyAdapter.MyViewHolder) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();
            Collections.swap(mData, from, to);
            mAdapter.notifyItemMoved(from, to);
            return true;
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(viewHolder instanceof MyAdapter.MyViewHolder) {
            int pos = viewHolder.getAdapterPosition();
            mData.remove(pos);
            mAdapter.notifyItemRemoved(pos);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE && viewHolder instanceof MyAdapter.MyViewHolder){
            MyAdapter.MyViewHolder holder = (MyAdapter.MyViewHolder)viewHolder;
            holder.itemView.setBackgroundColor(Color.BLUE);//设置拖拽和侧滑时的背景色
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if(viewHolder instanceof MyAdapter.MyViewHolder) {
            MyAdapter.MyViewHolder holder = (MyAdapter.MyViewHolder) viewHolder;
            holder.itemView.setBackgroundColor(0xffeeeeee); //背景色还原
        }
    }
}
