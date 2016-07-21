package com.qiyi.mvptest.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qiyi.mvptest.R;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;

public class RetrofitTestActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitTestActivity";

    private TextView tv1;
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        init();
        test();
    }

    public void init() {
        tv1 = (TextView) findViewById(R.id.tv_retro1);
        tv2 = (TextView) findViewById(R.id.tv_retro2);
    }

    public void test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetBaidu getBaidu = retrofit.create(GetBaidu.class);
        Call<ResponseBody> call = getBaidu.get();

//        try {
//            Response<ResponseBody> bodyResponse = call.execute();
//            String body = bodyResponse.body().string();
//            tv1.setText(body);
//            Log.e(TAG, body);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit.Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    String body = response.body().string();
                    tv2.setText(body);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
