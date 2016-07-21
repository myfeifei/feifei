package com.qiyi.mvptest.dagger;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }
}
