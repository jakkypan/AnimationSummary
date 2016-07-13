/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.learn.viewflipper.R;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * �����棺ViewPagerViewPager����android sdk �Դ�jar���У���Դgoogle �Ĳ������android-support-v4.jar
 */
public class ViewPagerActivity extends Activity {
	
	private ViewPager mViewPager;
	List<View> viewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
		LayoutInflater mInflater = getLayoutInflater().from(this);
		
		View v1 = mInflater.inflate(R.layout.layout1, null);
		View v2 = mInflater.inflate(R.layout.layout2, null);
		View v3 = mInflater.inflate(R.layout.layout3, null);
		
		//���ҳ�����
		viewList = new ArrayList<View>();
		viewList.add(v1);
		viewList.add(v2);
		viewList.add(v3);
		//ʵ��������
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new MyPagerAdapter(viewList));
		mViewPager.setCurrentItem(0); //����Ĭ�ϵ�ǰҳ
		
        View view = viewList.get(0);
        TextView textView = (TextView) view.findViewById(R.id.text_1);
        textView.setText("���ǵ�һҳ");
        Button button = (Button) view.findViewById(R.id.button_1);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "�����˰�ť", Toast.LENGTH_SHORT).show();
                }
        });
	}
}
