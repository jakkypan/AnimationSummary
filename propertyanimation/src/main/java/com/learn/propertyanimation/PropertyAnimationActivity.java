/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/7/3.
 */
public class PropertyAnimationActivity extends Activity {
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.tv_id);
    }

    /*
     * 单个动画
     */
    public void testObjectAnimator(View v) {
        float width = textView.getWidth();
        ObjectAnimator animator = null;
        if (textView.getX() == 0) {
            animator = ObjectAnimator.ofFloat(textView, "X", width);
        } else {
            animator = ObjectAnimator.ofFloat(textView, "X", 0f);
        }
        animator.setDuration(1000);
        animator.start();
    }

    /*
     * 连续动画
     */
    public void testAnimationSet(View v) {
        float width = textView.getWidth();
        float height = textView.getHeight();

        ObjectAnimator translationRight = ObjectAnimator.ofFloat(textView, "X", width);
        ObjectAnimator translationLeft = ObjectAnimator.ofFloat(textView, "X", 0f);
        ObjectAnimator translationDown = ObjectAnimator.ofFloat(textView, "Y", height);
        ObjectAnimator translationUp = ObjectAnimator.ofFloat(textView, "Y", 0);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(translationRight, translationDown);
        set.playSequentially(translationRight, translationLeft);
        set.playTogether(translationLeft, translationUp);
        set.setDuration(1500);
        set.start();
    }

    /*
	 * XML，便于代码重用
	 */
    public void testAnimationXML(View bView) {
        textView.setAlpha(1f);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fadein);
        set.setTarget(textView);
        set.start();
    }

    /*
	 * 一个动画改变多个属性值
	 */
    public void testPropertyValuesHolder(View v) {
        float h = textView.getHeight();
        float w = textView.getWidth();
        float x = textView.getX();
        float y = textView.getY();

        textView.setX(w);
        textView.setY(h);

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", x);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", y);

        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(textView, pvhX, pvhY);
        oa.setDuration(3000);
        oa.setInterpolator(new BounceInterpolator());
        oa.start();
    }

    /*
         * 一个View的多个属性进行动画，3.1中引入，对多属性动画进行了优化
         */
    public void testViewPropertyAnimator(View v) {
        float h = textView.getHeight();
        float w = textView.getWidth();
        float x = textView.getX();
        float y = textView.getY();

        textView.setX(w);
        textView.setY(h);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            ViewPropertyAnimator vpa = textView.animate().x(x).y(y);
            vpa.setDuration(1500);
            vpa.setInterpolator(new BounceInterpolator());
        }
    }

    /*
	 * 自定义Evaluator
	 */
    public void testTypeEvaluator(View v) {
        float h = textView.getHeight();
        float w = textView.getWidth();
        float x = textView.getX();
        float y = textView.getY();

        ObjectAnimator tea = ObjectAnimator.ofObject(new MyAnimatableView(textView), "point",
                new MyPointEvaluator(), new PointF(w, h), new PointF(x, y));
        tea.setDuration(2000);
        tea.setInterpolator(new OvershootInterpolator());
        tea.start();
    }

    /**
     * 关键帧
     *
     * @param v
     */
    public void testKeyFrames(View v) {
        float h = textView.getHeight();
        float w = textView.getWidth();
        float x = textView.getX();
        float y = textView.getY();

        Keyframe kf0 = Keyframe.ofFloat(0.2f, 360);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 30);
        Keyframe kf2 = Keyframe.ofFloat(0.8f, 1080);
        Keyframe kf3 = Keyframe.ofFloat(1f, 0);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2, kf3);
//        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat("rotation", 0, 360);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", w, x);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", h, y);
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(textView, pvhRotation, pvhX, pvhY);
        anim.setDuration(2000);
        anim.start();
    }

}
