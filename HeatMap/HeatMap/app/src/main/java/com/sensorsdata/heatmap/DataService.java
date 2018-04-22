package com.sensorsdata.heatmap;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangyunxiang on 2018/3/14.
 */

public interface DataService {
    @GET("top250")
    Observable<DataEntity> getTop250(@Query("start") int start,@Query("count") int count);
}
