package com.qiyi.mvptest.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qiyi.mvptest.R;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VolleyMainActivity extends AppCompatActivity {

    private String TAG = "VolleyMainActivity";
    private RequestQueue requestQueue;
    private TextView textView;
    private ImageView imageView;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_main);
        requestQueue = Volley.newRequestQueue(this);
        textView = (TextView) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.image);
        networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);
    }

    public void getVolley(View view) {
        StringRequest getStringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                textView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText(volleyError.getMessage());
            }
        });
        getStringRequest.setTag("abcGet");
        requestQueue.add(getStringRequest);
    }

    public void postVolley(View view) {
        StringRequest postStringRequest = new StringRequest(Request.Method.POST, "http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        textView.setText(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textView.setText(volleyError.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("param", "value");
                return map;
            }
        };
        requestQueue.add(postStringRequest);
    }

    public void jsonVolley(View view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        textView.setText(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textView.setText(volleyError.getMessage());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void imageVolley(View view) {
        String url = "http://img2.duitang.com/uploads/item/201301/23/20130123223055_YSSjC.thumb.600_0.jpeg";
//        ImageRequest imageRequest = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        imageView.setImageBitmap(bitmap);
//                    }
//                }, 0, 0, Bitmap.Config.RGB_565,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        imageView.setImageResource(R.drawable.buy);
//                    }
//                });
//        requestQueue.add(imageRequest);

        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.drawable.arrow, R.drawable.buy);
//        imageLoader.get(url, imageListener, 200, 200);

        networkImageView.setDefaultImageResId(R.drawable.buy);
        networkImageView.setErrorImageResId(R.drawable.leak_canary_icon);
        networkImageView.setImageUrl(url, imageLoader);
    }

    public void xmlVolley(View view) {
        String url = "http://flash.weather.com.cn/wmaps/xml/china.xml";
        XmlRequest xmlRequest = new XmlRequest(url,
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            int eventType = response.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        String nodeName = response.getName();
                                        if ("city".equals(nodeName)) {
                                            String pName = response.getAttributeValue(0);
                                            stringBuilder.append(pName).append(" ");
                                        }
                                        break;
                                }
                                eventType = response.next();
                            }
                            textView.setText(stringBuilder.toString());
                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textView.setText(volleyError.getMessage());
                    }
                });

        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(url, Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        StringBuilder stringBuilder = new StringBuilder();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        requestQueue.add(xmlRequest);
    }

    class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            return mCache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            mCache.put(s, bitmap);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.cancelAll("abcGet");
    }
}
