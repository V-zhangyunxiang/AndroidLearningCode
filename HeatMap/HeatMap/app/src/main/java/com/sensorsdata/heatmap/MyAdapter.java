package com.sensorsdata.heatmap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangyunxiang on 2018/1/20.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DataEntity.ObjectInfo> list;
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_FOOTER = 2;
    private static final int ITEM_TYPE_EMPTY = 3;
    //private static final String TAG = "zyx";

    public interface onMoreClickListener {
        void onMoreClick();
    }

    public MyAdapter(Context context, List<DataEntity.ObjectInfo> list) {
        this.context = context;
        this.list = list;
    }

   public void add(List<DataEntity.ObjectInfo> data){
       list.addAll(data);
       notifyDataSetChanged();
   }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            viewHolder = new MyViewHolder(view);

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", list.get(position - 1).getAlt());
                    context.startActivity(intent);
                }
            });

            StringBuilder stringBuilder = new StringBuilder();
            Iterator<String> it = list.get(position - 1).getGenres().iterator();
            while (it.hasNext()) {
                String object = it.next();
                stringBuilder.append(object + " ");
            }
            showImages(list.get(position - 1).getImages().getSmall(), ((MyViewHolder) holder).imageView);
            ((MyViewHolder) holder).tv_year.setText(list.get(position - 1).getYear());
            ((MyViewHolder) holder).tv_type.setText(stringBuilder);
            ((MyViewHolder) holder).tv_title.setText(list.get(position - 1).getTitle());
            ((MyViewHolder) holder).tv_score.setText(list.get(position - 1).getRating().getAverage());


        } else if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof FooterViewHolder) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      if(context instanceof onMoreClickListener){
                          ((onMoreClickListener) context).onMoreClick();
                      }
                }
            });

        } else if (holder instanceof EmptyViewHolder) {

        }

    }

    @Override
    public int getItemViewType(int position) {
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
        return (list.size() == 0) ? 1 : list.size() + 2;
    }

    public static class MyViewHolder extends ViewHolder {
        public TextView tv_title, tv_score, tv_year, tv_type;
        public ImageView imageView;
        public CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cv_image);
            tv_score = itemView.findViewById(R.id.cv_score);
            tv_title = itemView.findViewById(R.id.cv_title);
            tv_type = itemView.findViewById(R.id.cv_type);
            tv_year = itemView.findViewById(R.id.cv_year);
            cardView = itemView.findViewById(R.id.card_view);
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

    public void showImages(String url, ImageView image) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片质量,RGB_565节省内存,ARGB_8888原图显示
                .showImageForEmptyUri(R.mipmap.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnLoading(R.mipmap.ic_launcher)//默认图片
                .showImageOnFail(R.mipmap.ic_launcher)//加载失败显示的图片
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheSizePercentage(20)//设置占用内存的百分比
                .diskCacheFileCount(100) //可以缓存的文件数量
                .diskCacheSize(5 * 1024 * 1024)//sd卡(本地)缓存的最大值
                .defaultDisplayImageOptions(options)//设置加载的属性
                .build();
        ImageLoader.getInstance().init(configuration);//初始化配置
        //可以添加不同的options参数,来满足对图片形状的不同需求
        ImageLoader.getInstance().displayImage(url, image);
    }

}
