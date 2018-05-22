package com.sensorsdata.heatmap;

import android.app.Application;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyunxiang on 2017/11/15.
 */

public class HeatMapApplication extends Application {
    // 数据接收的 URL
    private final String SA_SERVER_URL = "http://zhaohaiying.cloud.sensorsdata.cn:8006/sa?project=production&token=9d8f18c23084485f";
    private final SensorsDataAPI.DebugMode SA_DEBUG_MODE = SensorsDataAPI.DebugMode.DEBUG_AND_TRACK;

    @Override
    public void onCreate() {
        super.onCreate();
        SensorsDataAPI.sharedInstance(this, SA_SERVER_URL, SA_DEBUG_MODE);
        //打开自动采集, 并指定追踪哪些 AutoTrack 事件
        List<SensorsDataAPI.AutoTrackEventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN);
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK);
        SensorsDataAPI.sharedInstance(this).enableAutoTrack(eventTypeList);
        SensorsDataAPI.sharedInstance(this).enableHeatMap();
        SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
    }
}
