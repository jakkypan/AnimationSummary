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

public abstract class GNowListAdapter extends GenericBaseAdapter {

    public GNowListAdapter(Context context, SpeedScrollListener scrollListener, List<? extends Object> items) {
        super(context, scrollListener, items);
    }

    @Override
    protected void defineInterpolator() {
        interpolator = new DecelerateInterpolator();
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
            v.setPivotX(width / 2);
            v.setPivotY(height / 2);
            v.setAlpha(0.0F);

            if (position % 2 == 0) {
                v.setTranslationX(-(width / 1.2F));
                v.setRotation(50);
            } else {
                v.setTranslationX((width / 1.2F));
                v.setRotation(-50);
            }

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
                v.animate()
                        .rotation(0.0F)
                        .translationX(0)
                        .translationY(0)
                        .setDuration(animDuration)
                        .alpha(1.0F)
                        .setInterpolator(interpolator);
            } else {
                AnimatorSet set = new AnimatorSet();

                ObjectAnimator rotationX = ObjectAnimator.ofFloat(v, "rotation", 0.0f);
                ObjectAnimator translationX = ObjectAnimator.ofFloat(v, "translationX", 0.0f);
                ObjectAnimator translationY = ObjectAnimator.ofFloat(v, "translationY", 0.0f);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1.0f);

                set.setDuration(animDuration);
                set.setInterpolator(interpolator);
                set.playTogether(rotationX, translationY, alpha);
                set.start();
            }


            positionsMapper.put(position, true);
        }

        return v;
    }

    protected abstract View getRowView(int position, View convertView, ViewGroup parent);
}