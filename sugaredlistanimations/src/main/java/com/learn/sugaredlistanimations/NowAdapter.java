/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.sugaredlistanimations;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/7/7.
 */
public class NowAdapter extends GNowListAdapter {
    private Context context;
    private static List<String> datas;

    static {
        datas = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            datas.add("i_" + i);
        }
    }

    protected NowAdapter(Context context, SpeedScrollListener scrollListener) {
        super(context, scrollListener, datas);
        this.context = context;
    }

    @Override
    protected View getRowView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.row_text);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.text.setText(datas.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView text;
    }
}
