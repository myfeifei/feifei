package com.qiyi.mvptest.eventbus;

/**
 * Created by kezhan_sx on 2016/7/4.
 */
public class FirstEvent {
    private String mMsg;

    public FirstEvent(String mMsg) {
        this.mMsg = mMsg;
    }

    public String getmMsg() {
        return mMsg;
    }
}
