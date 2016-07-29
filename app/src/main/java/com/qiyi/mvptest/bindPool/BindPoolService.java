
package com.qiyi.mvptest.bindPool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindPoolService extends Service {

    private Binder mBindPool = new BinderPool.BindPoolImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return mBindPool;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
