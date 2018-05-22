package com.sensorsdata.heatmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sensorsdata.analytics.android.sdk.SensorsDataTrackFragmentAppViewScreen;

@SensorsDataTrackFragmentAppViewScreen
public abstract class BaseFragment extends Fragment {

    protected boolean isViewInitiated;   //view是否加载，默认false
    protected boolean isDataInitiated;  //数据是否加载,默认false


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        //准备加载数据
        prepareFetchData();


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //准备加载数据
        prepareFetchData();
    }

    //加载数据，抽象方法，具体子类实现
    public abstract  void fetchData();

    public void prepareFetchData() {
        //判断如果用户可见，view加载成功，数据未加载
        if (getUserVisibleHint() && isViewInitiated && !isDataInitiated) {
            //加载数据
            fetchData();
            //数据加载成功
            isDataInitiated = true;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
