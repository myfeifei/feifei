package com.qiyi.mvptest.dagger0.di.modules;

import android.content.Context;

import com.qiyi.mvptest.dagger0.Navigator;
import com.qiyi.mvptest.dagger0.ToastUtil;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/19.
 */
@Module
public class AppMModule {

    Context context;

    public AppMModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides @Singleton
    public Navigator provideNavigator(){
        return new Navigator();
    }

    @Provides @Singleton
    public ToastUtil provideToastUtil(){
        return new ToastUtil(context);
    }

//    @Provides @Singleton
//    public Test provideTest(){
//        return new Test();
//    }
}
