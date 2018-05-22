package com.sensorsdata.heatmap;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DemoTwoFragment extends BaseFragment{
    private final static String TAG = "SA.DemoTwoFragment";
    public DemoTwoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_demo, container,false);

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);

    }

    @Override
    public void fetchData() {

    }
}
