package com.qiyi.mvptest.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qiyi.mvptest.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;

import retrofit.http.Multipart;

public class OkhttpFileActivity extends AppCompatActivity {

    private String filePath;
    final OkHttpClient okHttpClient = new OkHttpClient();
    private String url = "http://192.168.1.103:8080/okHttpServer/fileUpload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_file);
        uploadFile();
    }

    public void uploadFile() {
        File file = new File(filePath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-scream"), file);

        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"username\""),
                        RequestBody.create(null, "kezhan"))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"mFile\"; filename=\"wjd.mp4\""),
                        fileBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }
}
