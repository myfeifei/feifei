package com.qiyi.mvptest.dagger0.di.components;

import android.app.Activity;

import com.qiyi.mvptest.dagger0.di.modules.ActivityModule;
import com.qiyi.mvptest.dagger0.di.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
