/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.shimmer;

import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by panhongchao on 16/7/11.
 */
public class ShimmerViewHelper {
    public interface AnimationSetupCallback {
        void onSetupAnimation(View target);
    }

    private static final int DEFAULT_REFLECTION_COLOR = 0xFFFFFFFF;

    private View view;
    private Paint paint;
    private float gradientX;
    private float gradientY;
    private LinearGradient linearGradient;
    private Matrix linearGradientMatrix;
    private int primaryColor;
    private int reflectionColor;
    private boolean isShimmering;
    private boolean isSetUp;
    private boolean isVertical;

    private AnimationSetupCallback callback;

    public ShimmerViewHelper(View view, Paint paint, AttributeSet attributeSet) {
        this.view = view;
        this.paint = paint;
        init(attributeSet);
    }

    public float getGradientX() {
        return gradientX;
    }

    public void setGradientX(float gradientX) {
        this.gradientX = gradientX;
        view.invalidate();
    }

    public float getGradientY() {
        return gradientX;
    }

    public void setGradientY(float gradientY) {
        this.gradientY = gradientY;
        view.invalidate();
    }

    public boolean isShimmering() {
        return isShimmering;
    }

    public void setShimmering(boolean isShimmering) {
        this.isShimmering = isShimmering;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    public int getReflectionColor() {
        return reflectionColor;
    }

    public void setReflectionColor(int reflectionColor) {
        this.reflectionColor = reflectionColor;
        if (isSetUp) {
            resetLinearGradient();
        }
    }

    private void init(AttributeSet attributeSet) {
        reflectionColor = DEFAULT_REFLECTION_COLOR;

        if (attributeSet != null) {
            TypedArray a = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.ShimmerView, 0, 0);

            if (a != null) {
                reflectionColor = a.getColor(R.styleable.ShimmerView_reflectionColor, DEFAULT_REFLECTION_COLOR);
                isVertical = a.getBoolean(R.styleable.ShimmerView_isVertical, false);
                a.recycle();
            }
        }

        linearGradientMatrix = new Matrix();
    }

    private void resetLinearGradient() {
        if (isVertical) {
            linearGradient = new LinearGradient(0, 0, 0, -view.getHeight(),
                    new int[] {
                            primaryColor,
                            reflectionColor,
                            primaryColor,
                    },
                    new float[] {
                            0,
                            0.5f,
                            1
                    },
                    Shader.TileMode.CLAMP
            );
        } else {
            linearGradient = new LinearGradient(-view.getWidth(), 0, 0, 0,
                    new int[] {
                            primaryColor,
                            reflectionColor,
                            primaryColor,
                    },
                    new float[] {
                            0,
                            0.5f,
                            1
                    },
                    Shader.TileMode.CLAMP
            );
        }

        paint.setShader(linearGradient);
    }

    protected void onSizeChanged() {

        resetLinearGradient();

        if (!isSetUp) {
            isSetUp = true;

            if (callback != null) {
                callback.onSetupAnimation(view);
            }
        }
    }


    public void onDraw() {
        if (isShimmering) {
            if (paint.getShader() == null) {
                paint.setShader(linearGradient);
            }
            if (isVertical) {
                linearGradientMatrix.setTranslate(0, 2 * gradientY);
            } else {
                linearGradientMatrix.setTranslate(2 * gradientX, 0);
            }
            linearGradient.setLocalMatrix(linearGradientMatrix);
        } else {
            paint.setShader(null);
        }
    }
}
