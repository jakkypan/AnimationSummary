/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.viewpager;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mListView;

    public MyPagerAdapter(List<View> mListView) {
        super();
        this.mListView = mListView;
    }

    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewGroup) arg0).removeView(mListView.get(arg1));
    }

    public int getCount() {
        return mListView.size();
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewGroup) arg0).addView(mListView.get(arg1), 0);
        return mListView.get(arg1);
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

}

