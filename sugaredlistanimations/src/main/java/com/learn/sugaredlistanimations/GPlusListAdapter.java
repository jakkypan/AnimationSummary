/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.sugaredlistanimations;

import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by panhongchao on 16/7/7.
 */
public abstract class GPlusListAdapter extends GenericBaseAdapter {

    protected GPlusListAdapter(Context context, SpeedScrollListener scrollListener, List<? extends Object> items) {
        super(context, scrollListener, items);
    }

    @Override
    protected View getAnimatedView(int position, View convertView, ViewGroup parent) {
        v = getRowView(position, convertView, parent);

        if (v != null && !positionsMapper.get(position) && position > previousPositition) {
            speed = scrollListener.getSpeed();
            animDuration = (((int) speed) == 0) ? ANIM_DEFAULT_SPEED : (long) (1 / speed * 15000);

            if (animDuration > ANIM_DEFAULT_SPEED)
                animDuration = ANIM_DEFAULT_SPEED;

            previousPositition = position;

            v.setTranslationY(height);
            v.setRotationX(45.0f);
            v.setScaleX(0.7f);
            v.setScaleY(0.55f);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
                v.animate()
                        .translationY(0)
                        .rotationX(0.0f)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(animDuration)
                        .setInterpolator(interpolator);
            } else {
                AnimatorSet set = new AnimatorSet();

                ObjectAnimator rotationX = ObjectAnimator.ofFloat(v, "rotationX", 0.0f);
                ObjectAnimator translationY = ObjectAnimator.ofFloat(v, "translationY", 0.0f);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f);

                set.setDuration(animDuration);
                set.setInterpolator(interpolator);
                set.playTogether(rotationX, translationY, scaleX, scaleY);
                set.start();
            }
            positionsMapper.put(position, true);
        }

        return v;
    }

    @Override
    protected void defineInterpolator() {
        interpolator = new DecelerateInterpolator();
    }

    protected abstract View getRowView(int position, View convertView, ViewGroup parent);
}
