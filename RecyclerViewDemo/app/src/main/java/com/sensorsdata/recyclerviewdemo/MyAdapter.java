package com.sensorsdata.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangyunxiang on 2018/1/20.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<String> list;
    private RecyclerView recyclerView;
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;
    private static final int ITEM_TYPE_EMPTY = 3;
    private static final String TAG = "zyx";

    public void setOnChildClickLister(onChildClickListener listener) {
        this.listener = listener;
    }

    private onChildClickListener listener;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void remove(int position) {
        list.remove(position);
        //notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            listener.onChildClick(recyclerView, v, position, list.get(position));
        }
    }

    public interface onChildClickListener {
        void onChildClick(RecyclerView parent, View view, int position, String data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.i(TAG, "onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Log.i(TAG, "onDetachedFromRecyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == ITEM_TYPE_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
            viewHolder = new HeaderViewHolder(view);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_footer, parent, false);
            viewHolder = new FooterViewHolder(view);
        } else if (viewType == ITEM_TYPE_EMPTY) {
            view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
            viewHolder = new EmptyViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            view.setOnClickListener(this);
            viewHolder = new MyViewHolder(view);

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder");
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).text.setText(list.get(position - 1));
        }
/*            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "这是第" + position + "个数", Toast.LENGTH_SHORT).show();
                }
            });*/
        else if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof FooterViewHolder) {

        } else if (holder instanceof EmptyViewHolder) {

        }

    }

    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType");
        if (list.size() == 0) {
            return ITEM_TYPE_EMPTY;
        }
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else if (position + 1 == getItemCount()) {
            return ITEM_TYPE_FOOTER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount");
        return (list.size() == 0) ? 1 : list.size() + 2;
    }

    public static class MyViewHolder extends ViewHolder {
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG, "MyViewHolder");
            text = itemView.findViewById(R.id.item);
        }

    }

    public static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class EmptyViewHolder extends ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

/*    public static class MyViewHolder2 extends ViewHolder {
        private TextView text;

        public MyViewHolder2(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.item);
        }

    }*/
}
