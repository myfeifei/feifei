package com.qiyi.mvptest.bindPool;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.text.ICUCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiyi.mvptest.R;

public class BinderPoolActivity extends AppCompatActivity {

    BinderPool binderPool;
    IBinder computeBinder;
    ICompute iCompute;
    EditText a;
    EditText b;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        a = (EditText) findViewById(R.id.a);
        b = (EditText) findViewById(R.id.b);
        result = (TextView) findViewById(R.id.result);
        binderPool = BinderPool.getBinderPool(this);
        computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        iCompute = ComputeImpl.asInterface(computeBinder);
    }

    public void onClickCompute(View view) throws RemoteException {
        int aInt = Integer.parseInt(a.getText().toString());
        int bInt = Integer.parseInt(b.getText().toString());
        result.setText(String.valueOf(iCompute.add(aInt, bInt)));
    }
}
