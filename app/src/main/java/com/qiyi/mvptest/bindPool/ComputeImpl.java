package com.qiyi.mvptest.bindPool;

import android.os.RemoteException;

/**
 * Created by kezhan_sx on 2016/7/29.
 */
public class ComputeImpl extends ICompute.Stub{
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
