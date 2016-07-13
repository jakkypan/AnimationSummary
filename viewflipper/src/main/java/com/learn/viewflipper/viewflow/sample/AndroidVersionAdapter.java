/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.viewflow.sample;

import com.learn.viewflipper.R;
import com.learn.viewflipper.viewflow.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AndroidVersionAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater mInflater;

	private static final String[] versions = {"1.5","1.6","2.1","2.2","2.3","3.0","x.y" };
	private static final String[] names = {"Cupcake","Donut","Eclair","Froyo","Gingerbread","Honeycomb","IceCream Sandwich" };
	
	public AndroidVersionAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.flow_item, null);
		}
		((TextView) convertView.findViewById(R.id.textLabel)).setText(versions[position]);
		return convertView;
	}

	/* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	@Override
	public String getTitle(int position) {
		return names[position];
	}

}
