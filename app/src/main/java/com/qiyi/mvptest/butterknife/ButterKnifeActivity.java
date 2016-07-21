package com.qiyi.mvptest.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.qiyi.mvptest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

public class ButterKnifeActivity extends AppCompatActivity {
    private static final String TAG = "ButterKnifeActivity";

    @BindView(R.id.butter_tv)
    TextView butterTv;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.middle_name)
    EditText middleName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.list_view)
    ListView listView;

    private List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
        nameList = getData();
        Log.e(TAG, (listView == null) + "");
        listView.setAdapter(new NameAdapter(nameList, this));
    }

    public List<String> getData() {
        List<String> tmpList = new ArrayList<>();
        tmpList.add("鸣人");
        tmpList.add("佐助");
        tmpList.add("小樱");
        return tmpList;
    }

    @OnClick(R.id.submit)
    public void submit(View view) {
        firstName.setText("旗木");
    }

    @OnItemSelected(R.id.list_view)
    public void onItemSelected(int position) {
        Log.e(TAG, "onItemSelected");
    }

    @OnItemClick(R.id.list_view)
    public void onItemClick(int position) {
        butterTv.setText(nameList.get(position));
    }

    @OnFocusChange(R.id.first_name)
    public void onFocusChange(boolean focus) {
        if(focus) {
            middleName.setText("kaka");
        } else {
            middleName.setText("卡卡");
        }
    }

    @OnTextChanged(R.id.last_name)
    public void onTextChange(CharSequence text) {
        butterTv.setText(String.valueOf(text.length()));
    }
}
