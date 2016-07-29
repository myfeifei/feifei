package com.qiyi.mvptest.dagger0.di.components;

import android.content.Context;

import com.qiyi.mvptest.dagger0.Navigator;
import com.qiyi.mvptest.dagger0.ToastUtil;
import com.qiyi.mvptest.dagger0.di.modules.AppMModule;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by niuxiaowei on 16/3/19.
 */
@Singleton
@Component(modules={AppMModule.class})
public interface AppMComponent {

    Context getContext();
    Navigator getNavigator();
    ToastUtil getToastUtil();
//    Test test();

}
