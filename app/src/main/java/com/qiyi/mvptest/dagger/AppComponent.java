package com.qiyi.mvptest.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
@Singleton
@Component(modules = {AppModule.class, GithubApiModule.class})
public interface AppComponent {
    void inject(ReposListActivity activity);
}
