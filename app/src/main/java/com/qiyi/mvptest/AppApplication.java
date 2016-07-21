package com.qiyi.mvptest;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.qiyi.mvptest.dagger.AppComponent;
import com.qiyi.mvptest.dagger.AppModule;
import com.qiyi.mvptest.dagger.DaggerAppComponent;
import com.qiyi.mvptest.dagger.GithubApiModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public class AppApplication extends MultiDexApplication {

    private static AppApplication sInstance;
    private AppComponent appComponent;

    public static AppApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
        LeakCanary.install(this);
        setupComponent();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .githubApiModule(new GithubApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
