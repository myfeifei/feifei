package com.qiyi.mvptest.dagger0.di.components;

import com.qiyi.mvptest.dagger0.di.modules.ActivityModule;
import com.qiyi.mvptest.dagger0.di.modules.MainModule;
import com.qiyi.mvptest.dagger0.di.scopes.PerActivity;
import com.qiyi.mvptest.dagger0.view.Dagger0MainActivity;

import dagger.Component;

/**
 * MainComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppMComponent.class,modules = {MainModule.class, ActivityModule.class})
public interface MainComponent extends ActivityComponent{
    //对MainActivity进行依赖注入
    void inject(Dagger0MainActivity mainActivity);

    MainFragmentComponent mainFragmentComponent();
}
