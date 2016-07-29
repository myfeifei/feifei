package com.qiyi.mvptest.bindPool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kezhan_sx on 2016/7/29.
 */
public class BinderPool {
    public static final int BINDER_NONE = -1;
    public static final int BINDER_COMPUTE = 0;

    private Context mContext;
    private IBindPool mBindPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch latch;

    private BinderPool(Context mContext) {
        this.mContext = mContext;
        connectBindPoolService();
    }

    public static BinderPool getBinderPool(Context mContext) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(mContext);
                }
            }
        }
        return sInstance;
    }

    public IBinder queryBinder(int bindCode) {
        IBinder binder = null;
        try {
            if (mBindPool != null) {
                binder = mBindPool.queryBinder(bindCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

    private synchronized void connectBindPoolService() {
        latch = new CountDownLatch(1);
        Intent service = new Intent(mContext, BindPoolService.class);
        mContext.bindService(service, mBindPoolConnection, Context.BIND_AUTO_CREATE);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mBindPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBindPool = IBindPool.Stub.asInterface(iBinder);
            try {
                mBindPool.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mBindPool.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mBindPool = null;

        }
    };

    public static class BindPoolImpl extends IBindPool.Stub {

        @Override
        public IBinder queryBinder(int bindCode) throws RemoteException {
            IBinder binder = null;
            switch (bindCode) {
                case BINDER_COMPUTE:
                    binder = new ComputeImpl();
                    break;
                default:
                    break;
            }
            return binder;
        }
    }
}
