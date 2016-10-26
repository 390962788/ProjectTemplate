package com.guoyizeng.mvpbase.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseCommonViewHolder {
	private final SparseArray<View> mView;
	private View mConvertView;

	public BaseCommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mView = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static BaseCommonViewHolder getViewHolder(View convertView, Context context, ViewGroup parent, int layoutId, int position) {
		if (null == convertView) {
			return new BaseCommonViewHolder(context, parent, layoutId, position);
		}
		return (BaseCommonViewHolder) convertView.getTag();
	}

	/**
	 * @param id
	 * @return
	 */
	public <T extends View> T getView(int id) {
		View view = mView.get(id);
		if (null == view) {
			view = mConvertView.findViewById(id);
			mView.put(id, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}
}