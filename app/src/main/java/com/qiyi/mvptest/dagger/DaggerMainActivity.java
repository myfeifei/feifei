package com.qiyi.mvptest.dagger;

import android.content.Intent;
import android.os.Bundle;

import com.qiyi.mvptest.R;

import butterknife.OnClick;

public class DaggerMainActivity extends BaseActivity {

    @OnClick(R.id.id_main_button)
    public void onShowRepositoriesClick() {
        startActivity(new Intent(this, ReposListActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId(){
        return R.layout.activity_dagger_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent){

    }
}
