package com.qiyi.mvptest.picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiyi.mvptest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhan on 2016/7/21.
 */
public class lv_Adapter extends BaseAdapter {

	private List<News> list_new;
	private LayoutInflater inflater;
	private Context context;

	public lv_Adapter(List<News> list_new, Context context) {
		this.list_new = list_new;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list_new.size();
	}

	@Override
	public Object getItem(int position) {
		return list_new.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ItemNews in;
		if(convertView==null)
		{
			in = new ItemNews();

			convertView = inflater.inflate(R.layout.lv_item,null);
			in.title = (TextView)convertView.findViewById(R.id.txt_title);
			in.contents = (TextView)convertView.findViewById(R.id.txt_content);
			in.pic = (ImageView)convertView.findViewById(R.id.item_pic);

			convertView.setTag(in);
		}else
		{
			in = (ItemNews)convertView.getTag();
		}

		in.contents.setText(list_new.get(position).getContents());
		in.title.setText(list_new.get(position).getTitle());

		//注意这里，一行代码，就把图片加载上去了，而且你上下滚动listView你会发现什么，这个大家自己去体会
		Picasso.with(context).load(list_new.get(position).getPicUrl()).fit()
				.placeholder(R.drawable.pic)
				.error(R.drawable.buy)
				.into(in.pic);

		return convertView;
	}


	class ItemNews
	{
		TextView title;
		ImageView pic;
		TextView contents;
	}
}
