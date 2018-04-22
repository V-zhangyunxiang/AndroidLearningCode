package com.sensorsdata.heatmap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
//implements ScreenAutoTracker

public class DemoFragment extends BaseFragment  {

    private ArrayList<Fragment> mDatas;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private DemoFragment_One mDemoFragment_One;
    private DemoFragment_Two mDemoFragment_Two;
    private FragmentStatePagerAdapter mFSPagerAdapter;
    private RadioButton mRadioButton_one, mRadioButton_two;
    private RadioButton[] mStore;
    private final static String TAG = "SA.DemoFragment";

    public DemoFragment() {

    }

    @Override
    public void fetchData() {
        Log.i(TAG, "DemoFragment-fetchData");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "DemoFragment-onCreateView");
        View view = inflater.inflate(R.layout.fragment_demo, container,false);
        mRadioGroup = view.findViewById(R.id.rg_page);
        mViewPager = view.findViewById(R.id.vp_demo_fragment);
        mRadioButton_one = view.findViewById(R.id.rb_one);
        mRadioButton_two = view.findViewById(R.id.rb_two);
        mDatas = new ArrayList<>();
        mStore = new RadioButton[]{mRadioButton_one, mRadioButton_two};
        mDemoFragment_One = new DemoFragment_One();
        mDemoFragment_Two = new DemoFragment_Two();
        mDatas.add(mDemoFragment_One);
        mDatas.add(mDemoFragment_Two);

        mFSPagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }
        };

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_two:
                        mViewPager.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mStore[position].setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(mFSPagerAdapter);
        mViewPager.setCurrentItem(0);

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    public void updateData(){
        mDemoFragment_One.updateData();
    }

   /* @Override
    public String getScreenUrl() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public JSONObject getTrackProperties() throws JSONException {
        return null;
    }*/
}
