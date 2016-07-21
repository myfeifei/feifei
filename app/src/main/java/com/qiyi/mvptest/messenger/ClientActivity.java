package com.qiyi.mvptest.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiyi.mvptest.R;

public class ClientActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";
    private static final int MSG_NUM = 0x110;

    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    private TextView mTvState;

    private Messenger mService;
    private boolean isConn;

    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromSever) {
            switch (msgFromSever.what) {
                case MSG_NUM:
                    TextView tv = (TextView) mLyContainer.findViewById(msgFromSever.arg1);
                    tv.setText(tv.getText() + "" + msgFromSever.arg2);
            }
            super.handleMessage(msgFromSever);
        }
    });

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            isConn = true;
            mTvState.setText("connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
            isConn = false;
            mTvState.setText("disconnected");
        }
    };

    private int mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        bindServiceInvoked();

        mTvState = (TextView) findViewById(R.id.tv_callback);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mLyContainer = (LinearLayout) findViewById(R.id.ll_container);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = mA++;
                int b = (int) (Math.random() * 100);

                TextView tv = new TextView(ClientActivity.this);
                tv.setText(a + " + " + b + " = ");
                tv.setId(a);
                mLyContainer.addView(tv);

                Message msgFromClient = Message.obtain(null, MSG_NUM, a, b);
                msgFromClient.replyTo = mMessenger;

                if(isConn) {
                    try {
                        mService.send(msgFromClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void bindServiceInvoked() {
        Intent intent = new Intent(ClientActivity.this, MessengerService.class);
        bindService(intent, mConn, BIND_AUTO_CREATE);
    }

}
