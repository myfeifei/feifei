package com.qiyi.mvptest.okhttp.myokhttp0.builder;

import com.qiyi.mvptest.okhttp.myokhttp0.OkHttpUtils;
import com.qiyi.mvptest.okhttp.myokhttp0.request.OtherRequest;
import com.qiyi.mvptest.okhttp.myokhttp0.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
