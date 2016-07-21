// (c)2016 Flipboard Inc, All Rights Reserved.

package com.qiyi.mvptest.rxjava.network.api;


import com.qiyi.mvptest.rxjava.model.ZhuangbiImage;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ZhuangbiApi {
    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
