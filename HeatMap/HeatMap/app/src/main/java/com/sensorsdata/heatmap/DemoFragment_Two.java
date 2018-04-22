package com.sensorsdata.heatmap;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//implements ScreenAutoTracker

public class DemoFragment_Two extends BaseFragment {
    private final static String TAG = "SA.DemoFragment_One";

    public DemoFragment_Two() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "DemoFragment_Two-onCreateView");
        return inflater.inflate(R.layout.fragment_demo_two, container,false);
    }

    /*@Override
    public String getScreenUrl() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public JSONObject getTrackProperties() throws JSONException {
        return null;
    }*/

    @Override
    public void fetchData() {
        Log.i(TAG, "DemoFragment_Two-fetchData");
    }
}
