/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.shimmer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * Created by panhongchao on 16/7/12.
 */
public class MainActivity extends Activity {

    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        tv.setTextColor(Color.RED);
        tv.setReflectionColor(Color.GREEN);
        tv.setPrimaryColor(Color.YELLOW);
//        tv.setGradientX(0.5f);
    }

    public void toggleAnimation(View target) {
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.setDirection(Shimmer.ANIMATION_DIRECTION_BTT);
            shimmer.start(tv);
        }
    }
}