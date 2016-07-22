package com.qiyi.mvptest.dagger0.di.components;

import com.qiyi.mvptest.dagger0.di.scopes.PerActivity;
import com.qiyi.mvptest.dagger0.view.MainFragment;

import dagger.Subcomponent;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Subcomponent
public interface MainFragmentComponent {


    void inject(MainFragment mainFragment);
}
