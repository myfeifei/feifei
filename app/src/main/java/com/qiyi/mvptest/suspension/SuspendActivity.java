package com.qiyi.mvptest.suspension;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.qiyi.mvptest.R;

public class SuspendActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {

    private static final String TAG = "SuspendActivity";

    /**
     * 自定义的MyScrollView
     */
    private MyScrollView myScrollView;
    /**
     * 在MyScrollView里面的购买布局
     */
    private LinearLayout mBuyLayout;
    /**
     * 位于顶部的购买布局
     */
    private LinearLayout mTopBuyLayout;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend);

        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (LinearLayout) findViewById(R.id.buy);
        mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);

        myScrollView.setOnScrollListener(this);

        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        Log.e(TAG, "Scroll_Y: " + scrollY + ":" + mBuyLayout.getTop());
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }

}
