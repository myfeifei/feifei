package com.qiyi.mvptest.retrofit;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by kezhan_sx on 2016/7/4.
 */
public interface GetBaidu {
    @GET("http://www.baidu.com/")
    Call<ResponseBody> get();
}
