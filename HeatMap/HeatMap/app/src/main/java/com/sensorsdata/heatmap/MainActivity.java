package com.sensorsdata.heatmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

//implements ScreenAutoTracker
public class MainActivity extends AppCompatActivity  implements MyAdapter.onMoreClickListener{

    private RadioGroup mRadioGroup;
    private FrameLayout mFrameLayout;
    private FragmentStatePagerAdapter mFSPagerAdapter;
    private DemoFragment demoFragment;
    private DemoTwoFragment demoTwoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = findViewById(R.id.rg_main_page);
        mFrameLayout = findViewById(R.id.fl_fragment);

        initFragmentAdapter();
        initFragment();

    }

    //初始化fragment的适配器
    private void initFragmentAdapter() {
        if(demoFragment == null){
            demoFragment= new DemoFragment();
        }
        if(demoTwoFragment == null){
            demoTwoFragment = new DemoTwoFragment();
        }
        mFSPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            public int getCount() {
                return 2;
            }

            public Fragment getItem(int arg0) {
                switch (arg0) {
                    case 0:
                        return demoFragment;
                    case 1:
                        return demoTwoFragment;
                    default:
                        return null;
                }
            }
        };

    }

    private void initFragment() {
        //首次进入默认设置显示第一个fragment
        Fragment fragment = (Fragment) mFSPagerAdapter.instantiateItem(mFrameLayout, 0);
        mFSPagerAdapter.setPrimaryItem(mFrameLayout, 0, fragment);
        mFSPagerAdapter.finishUpdate(mFrameLayout);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId){
                    case R.id.rb_one:
                        index = 0;
                        break;
                    case R.id.rb_two:
                        index = 1;
                        break;
                        default:
                            break;
                }
                Fragment fragment1 = (Fragment) mFSPagerAdapter.instantiateItem(mFrameLayout, index);
                mFSPagerAdapter.setPrimaryItem(mFrameLayout, 0, fragment1);
                mFSPagerAdapter.finishUpdate(mFrameLayout);
            }
        });
    }

    @Override
    public void onMoreClick() {
        demoFragment.updateData();
    }


   /* @Override
    public String getScreenUrl() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public JSONObject getTrackProperties() throws JSONException {
        return null;
    }
*/

}
