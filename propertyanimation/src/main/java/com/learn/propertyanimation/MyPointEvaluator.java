/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.propertyanimation;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by panhongchao on 16/7/5.
 */
public class MyPointEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return new PointF(
                startValue.x + fraction * (endValue.x - startValue.x),
                startValue.y + fraction * (endValue.y - startValue.y)
        );
    }
}
