package com.qiyi.mvptest.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qiyi.mvptest.R;

import de.greenrobot.event.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onFirstEventBtnClick(View view) {
        String msg = "hello, first event btn clicked!";
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new FirstEvent(msg));
    }
}
