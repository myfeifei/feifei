// IBindPool.aidl
package com.qiyi.mvptest.bindPool;

// Declare any non-default types here with import statements

interface IBindPool {
    IBinder queryBinder(int bindCode);
}
