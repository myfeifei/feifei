package com.qiyi.mvptest.handlerThread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.qiyi.mvptest.messenger.ClientActivity;
import com.qiyi.mvptest.R;

public class HandlerThreadActivity extends AppCompatActivity {

    private static final String TAG = "HandlerThreadActivity";

    private Handler superHandler;
    private Handler normalHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_thread);

        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();

        superHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 2:
                        Log.e(TAG, Thread.currentThread().getName() + " is OK");
                        break;
                }
            }
        };

        NormalHandler normalHandler = new NormalHandler();
        normalHandler.start();
    }

    class NormalHandler extends Thread {
        public void run() {
            Looper.prepare();

            normalHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            Log.e(TAG, Thread.currentThread().getName() + " NormalHandler is OK");
                            break;
                    }
                }
            };

            Looper.loop();
        }
    }

    public void onHandlerThreadUse(View view) {
        if(superHandler != null) {
            superHandler.sendEmptyMessage(2);
        }
    }

    public void onNormalThreadUse(View view) {
        if(normalHandler != null) {
            normalHandler.sendEmptyMessage(1);
        }
    }

    public void onMessengerTest(View view) {
        Intent intent = new Intent(HandlerThreadActivity.this, ClientActivity.class);
        startActivity(intent);
    }
}
