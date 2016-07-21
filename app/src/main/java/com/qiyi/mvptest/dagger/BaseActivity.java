package com.qiyi.mvptest.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qiyi.mvptest.AppApplication;

import butterknife.ButterKnife;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(AppApplication.getInstance().getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
    protected abstract int getLayoutId();
}
