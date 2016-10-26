package com.guoyizeng.mvpbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseCommonAdapter<T> extends BaseAdapter {
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected Context mContext;

	public BaseCommonAdapter(Context ctx, List<T> lists) {
		this.mDatas = lists;
		mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = ctx;
	}

	public void setData(List<T> mDatas){
		this.mDatas = mDatas;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseCommonViewHolder vh = BaseCommonViewHolder.getViewHolder(convertView, mContext, parent, layoutResId(), position);
		convert(position,vh, mDatas.get(position));
		return vh.getConvertView();
	}
	abstract protected int layoutResId();
	abstract protected void convert(int position,BaseCommonViewHolder vh, T item);
}
