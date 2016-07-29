package com.qiyi.mvptest.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.qiyi.mvptest.aidl.Book;

/**
 * Created by kezhan_sx on 2016/7/1.
 */
public class MessengerService extends Service {

    private static final int MSG_NUM = 0x110;

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromClient) {
            Message msgToClient = Message.obtain(msgFromClient);
            switch (msgFromClient.what) {
                case MSG_NUM:
                    msgToClient.what = MSG_NUM;
                    try {
                        Thread.sleep(2000);
                        msgToClient.arg2 = msgFromClient.arg1 + msgFromClient.arg2;
                        Book book = new Book(0, "Zero");
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("book", book);
                        msgToClient.setData(bundle);
                        msgFromClient.replyTo.send(msgToClient);
                    } catch (InterruptedException | RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

            }
            super.handleMessage(msgFromClient);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
