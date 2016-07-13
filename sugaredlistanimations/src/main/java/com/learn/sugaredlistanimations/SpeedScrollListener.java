/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.sugaredlistanimations;

import android.widget.AbsListView;

/**
 * Created by panhongchao on 16/7/6.
 */
public class SpeedScrollListener implements AbsListView.OnScrollListener {
    private int previousFirstVisibleItem = 0;
    private long previousEventTime = 0, currTime, timeToScrollOneElement;
    private double speed = 0;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (previousFirstVisibleItem != firstVisibleItem) {
            currTime = System.currentTimeMillis();
            timeToScrollOneElement = currTime - previousEventTime;
            speed = ((double) 1 / timeToScrollOneElement) * 1000;

            previousFirstVisibleItem = firstVisibleItem;
            previousEventTime = currTime;
        }
    }

    public double getSpeed() {
        return speed;
    }
}
