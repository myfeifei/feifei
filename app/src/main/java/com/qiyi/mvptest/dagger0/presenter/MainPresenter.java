package com.qiyi.mvptest.dagger0.presenter;

import com.qiyi.mvptest.dagger0.data.GetUserData;
import com.qiyi.mvptest.dagger0.data.UserData;

import javax.inject.Inject;

/**
 * Created by niuxiaowei on 16/3/20.
 */
public class MainPresenter {

    public GetUserData mUserData;
    private IUserView mUserView;



    @Inject
    public MainPresenter(GetUserData userData){
        this.mUserData = userData;
    }

    public void getUser(){
        UserData userData = this.mUserData.getUser();
        this.mUserView.setUserName(userData.mUserName);
    }
    public void setUserView(IUserView userView){
        this.mUserView = userView;
    }

    public static interface IUserView{
        void setUserName(String name);
    }
}
