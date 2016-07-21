package com.qiyi.mvptest.butterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiyi.mvptest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public class NameAdapter extends BaseAdapter {
    private static final String TAG = "NameAdapter";

    private List<String> nameList;
    private LayoutInflater mInflater;

    public NameAdapter(List<String> nameList, Context mContext) {
        this.nameList = nameList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return nameList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null) {
            view = mInflater.inflate(R.layout.item_name, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        setData(holder, nameList.get(i));
        return view;
    }

    public void setData(ViewHolder holder, String name) {
        holder.mName.setText(name);
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView mName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
