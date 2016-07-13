/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.viewflow.sample;

import com.learn.viewflipper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	//ͼƬ��Դ��id
	private static final int[] ids = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
		R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h};

	public ImageAdapter(Context context) {
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ids == null ? 0 :ids.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
		}
		((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(ids[position]);
		return convertView;
	}
}