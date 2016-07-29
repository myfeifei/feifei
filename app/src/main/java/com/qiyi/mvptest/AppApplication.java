package com.qiyi.mvptest;

import android.support.multidex.MultiDexApplication;

import com.qiyi.mvptest.dagger.AppComponent;
import com.qiyi.mvptest.dagger.AppModule;
import com.qiyi.mvptest.dagger.DaggerAppComponent;
import com.qiyi.mvptest.dagger.GithubApiModule;
import com.qiyi.mvptest.dagger0.di.components.AppMComponent;
import com.qiyi.mvptest.dagger0.di.components.DaggerAppMComponent;
import com.qiyi.mvptest.dagger0.di.modules.AppMModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public class AppApplication extends MultiDexApplication {

    private static AppApplication sInstance;
    private AppComponent appComponent;
    AppMComponent mAppComponent;

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
        mAppComponent = DaggerAppMComponent.builder()
                .appMModule(new AppMModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppMComponent getAppMComponent() {
        return mAppComponent;
    }
}
