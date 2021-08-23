package com.sensorsdata.heatmap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

//implements ScreenAutoTracker
public class DemoFragment_One extends BaseFragment {

    private MyAdapter myAdapter;
    private Context context;
    private final static String TAG = "zyx";
    private RecyclerView recyclerView;
    public int index = 0;

    public DemoFragment_One() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "DemoFragment_One-onCreateView");
        View view = inflater.inflate(R.layout.fragment_demo_one, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
               int totalItemCount = recyclerView.getAdapter().getItemCount();
               int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
               int visibleItemCount = recyclerView.getChildCount();

               if (newState == RecyclerView.SCROLL_STATE_IDLE
                       && lastVisibleItemPosition == totalItemCount - 1
                       && visibleItemCount > 0) {
                   //加载更多
               }
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);

           }

       });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;

    }


   /* @Override
    public String getScreenUrl() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public JSONObject getTrackProperties() throws JSONException {
        return null;
    }*/

    @Override
    public void fetchData() {
        Log.i(TAG, "DemoFragment_One-fetchData");
      Observable<DataEntity> observable = RetrofitManager.getInstance(context).create(DataService.class).getTop250(index, 10);
        observable.observeOn(AndroidSchedulers.mainThread(), true)
                .map(new Func1<DataEntity, List<DataEntity.ObjectInfo>>() {

                    @Override
                    public List<DataEntity.ObjectInfo> call(DataEntity dataEntity) {
                        return dataEntity.getSubjects();
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(
                        new Subscriber<List<DataEntity.ObjectInfo>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "--onError" + e.getMessage());
                            }

                            @Override
                            public void onNext(List<DataEntity.ObjectInfo> objectInfo) {
                                if (myAdapter == null) {
                                    myAdapter = new MyAdapter(context, objectInfo);
                                    recyclerView.setAdapter(myAdapter);
                                }else{
                                    myAdapter.add(objectInfo);
                                }
                            }

                            /* ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchCallback(myAdapter, objectInfo));
                               helper.attachToRecyclerView(recyclerView);*/
                            @Override
                            public void onStart() {
                                super.onStart();

                            }
                        }

                );

    }

    public void updateData() {
        index += 10;
        fetchData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RetrofitManager.getInstance(context).clearContext();
    }
}
