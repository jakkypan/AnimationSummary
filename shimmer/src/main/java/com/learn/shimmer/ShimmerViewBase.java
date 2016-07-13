/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.shimmer;

/**
 * Created by panhongchao on 16/7/11.
 */
public interface ShimmerViewBase {
    public float getGradientX();

    public void setGradientX(float gradientX);

    public float getGradientY();

    public void setGradientY(float gradientX);

    public boolean isShimmering();

    public void setShimmering(boolean isShimmering);

    public boolean isSetUp();

    public boolean isVertical();

    public void setAnimationSetupCallback(ShimmerViewHelper.AnimationSetupCallback callback);

    public int getPrimaryColor();

    public void setPrimaryColor(int primaryColor);

    public int getReflectionColor();

    public void setReflectionColor(int reflectionColor);
}
