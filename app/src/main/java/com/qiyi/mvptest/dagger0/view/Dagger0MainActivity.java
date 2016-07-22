package com.qiyi.mvptest.dagger0.view;

import android.net.Uri;
import android.os.Bundle;

import com.qiyi.mvptest.R;
import com.qiyi.mvptest.dagger0.di.components.MainComponent;
import com.qiyi.mvptest.dagger0.di.modules.ActivityModule;
import com.qiyi.mvptest.dagger0.di.modules.MainModule;

public class Dagger0MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener{

    private MainComponent mMainComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mMainComponent = DaggerMainComponent.builder().appComponent(getAppComponent())
//                .mainModule(new MainModule())
//                .activityModule(new ActivityModule(this)).build();
//        mMainComponent.inject(this);
    }

    public MainComponent getMainComponent(){
        return mMainComponent;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
