package com.qiyi.mvptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyi.mvptest.gson.GsonMainActivity;
import com.qiyi.mvptest.aidl.AidlMainActivity;
import com.qiyi.mvptest.dagger.DaggerMainActivity;
import com.qiyi.mvptest.butterknife.ButterKnifeActivity;
import com.qiyi.mvptest.eventbus.EventBusActivity;
import com.qiyi.mvptest.handlerThread.HandlerThreadActivity;
import com.qiyi.mvptest.messenger.ClientActivity;
import com.qiyi.mvptest.picasso.PicassoMainActivity;
import com.qiyi.mvptest.pulltorefresh.PullMainActivity;
import com.qiyi.mvptest.retrofit.RetrofitTestActivity;
import com.qiyi.mvptest.rxjava.RxJavaMainActivity;
import com.qiyi.mvptest.suspension.SuspendActivity;
import com.qiyi.mvptest.volley.VolleyMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickHandlerThread(View view) {
        Intent intent = new Intent(this, HandlerThreadActivity.class);
        startActivity(intent);
    }

    public void onClickMessenger(View view) {
        Intent intent = new Intent(this, ClientActivity.class);
        startActivity(intent);
    }

    public void onClickSuspensionFrame(View view) {
        Intent intent = new Intent(this, SuspendActivity.class);
        startActivity(intent);
    }

    public void onClickEventBus(View view) {
        Intent intent = new Intent(this, EventBusActivity.class);
        startActivity(intent);
    }

    public void onClickRetrofit(View view) {
        Intent intent = new Intent(this, RetrofitTestActivity.class);
        startActivity(intent);
    }

    public void onClickButterKnife(View view) {
        Intent intent = new Intent(this, ButterKnifeActivity.class);
        startActivity(intent);
    }

    public void onClickDagger(View view) {
        Intent intent = new Intent(this, DaggerMainActivity.class);
        startActivity(intent);
    }

    public void onClickRxJava(View view) {
        Intent intent = new Intent(this, RxJavaMainActivity.class);
        startActivity(intent);
    }

    public void onClickAidl(View view) {
        Intent intent = new Intent(this, AidlMainActivity.class);
        startActivity(intent);
    }

    public void onClickPullToRefresh(View view) {
        Intent intent = new Intent(this, PullMainActivity.class);
        startActivity(intent);
    }

    public void onClickVolley(View view) {
        Intent intent = new Intent(this, VolleyMainActivity.class);
        startActivity(intent);
    }

    public void onClickGson(View view) {
        Intent intent = new Intent(this, GsonMainActivity.class);
        startActivity(intent);
    }

    public void onClickPicasso(View view) {
        Intent intent = new Intent(this, PicassoMainActivity.class);
        startActivity(intent);
    }
}
