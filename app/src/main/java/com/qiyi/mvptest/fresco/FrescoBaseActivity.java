package com.qiyi.mvptest.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiyi.mvptest.R;

public class FrescoBaseActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco_base);
        initView();
    }

    private void initView() {
        //创建SimpleDraweeView对象
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.main_sdv);
        //创建将要下载的图片的URI
        Uri imageUri = Uri.parse("http://my.csdn.net/uploads/avatar/4/E/8/1_y1scp.jpg");
        //开始下载
        simpleDraweeView.setImageURI(imageUri);

        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //重试之后要加载的图片URI地址
                .setUri(imageUri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                //构建
                .build();

        //设置DraweeController
        simpleDraweeView.setController(controller);
    }
}
