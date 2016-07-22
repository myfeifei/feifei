package com.qiyi.mvptest.dagger0.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qiyi.mvptest.AppApplication;
import com.qiyi.mvptest.dagger0.di.components.AppMComponent;

/**
 * Created by niuxiaowei on 16/3/20.
 */
public class BaseActivity extends AppCompatActivity {

    public AppMComponent getAppComponent(){
        return ((AppApplication)getApplication()).getAppMComponent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
