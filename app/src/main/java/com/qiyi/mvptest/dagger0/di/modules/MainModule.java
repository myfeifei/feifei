package com.qiyi.mvptest.dagger0.di.modules;

import com.qiyi.mvptest.dagger0.data.GetUserData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@Module
public class MainModule {

    @Provides
    public GetUserData provideUserData(){
        return new GetUserData();
    }

}
