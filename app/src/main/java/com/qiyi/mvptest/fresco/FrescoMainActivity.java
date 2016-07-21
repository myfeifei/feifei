package com.qiyi.mvptest.fresco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiyi.mvptest.R;

public class FrescoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_main);
    }

    public void onClickBase(View view) {
        Intent intent = new Intent(this, FrescoBaseActivity.class);
        startActivity(intent);
    }

    public void onClickHierarchy(View view) {
        Intent intent = new Intent(this, FrescoHierarchyActivity.class);
        startActivity(intent);
    }

    public void onClickForRound(View view) {
        Intent intent = new Intent(this, FrescoForRoundActivity.class);
        startActivity(intent);
    }
}
