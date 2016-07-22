package com.qiyi.mvptest.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qiyi.mvptest.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class OkhttpPostActivity extends AppCompatActivity {

    private String url = "http://www.wooyun.org";
    private final OkHttpClient okHttpClient = new OkHttpClient();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_post);
        textView = (TextView) findViewById(R.id.content);
        postRequest();
    }

    private void postRequest() {

        RequestBody formBody = new FormEncodingBuilder()
                .add("username","kezhan")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String str = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(str);
                    }
                });
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    response = okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()) {
////                        Log.i("WY","打印POST响应的数据：" + response.body().string());
//                        textView.setText(response.body().string());
//                    } else {
//                        throw new IOException("Unexpected code " + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

}
