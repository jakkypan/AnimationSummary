/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.flipper;

import com.learn.viewflipper.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * ViewFlipper ???????
 * @author ZHF
 *
 */
public class MainActivity extends Activity implements OnTouchListener{

    private ViewFlipper viewFlipper;  
    private float touchDownX;  // ????????X???  
    private float touchUpX;  //????????X???  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);
  
        viewFlipper = (ViewFlipper) findViewById(R.id.body_flipper);  
        viewFlipper.setOnTouchListener(this);  
    }  
  
    @Override  
    public boolean onTouch(View v, MotionEvent event) {  
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchDownX = event.getX();  
            return true;  
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            touchUpX = event.getX();
            if (touchUpX - touchDownX > 100) {
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,  
                       R.anim.push_right_in));  
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,  
                        R.anim.push_right_out));
                viewFlipper.showPrevious();
            } else if (touchDownX - touchUpX > 100) {
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,  
                        R.anim.push_left_in));  
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,  
                        R.anim.push_left_out));
                viewFlipper.showNext();  
            }  
            return true;  
        }  
        return false;  
    }  
}  