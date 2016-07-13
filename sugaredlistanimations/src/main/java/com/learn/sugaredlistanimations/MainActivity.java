/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.sugaredlistanimations;

import android.os.Bundle;

import android.app.ListActivity;
        import android.view.Menu;
        import android.view.MenuItem;

public class MainActivity extends ListActivity {

    private SpeedScrollListener listener;
    private PlusAdapter plusAdapter;
    private NowAdapter nowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new SpeedScrollListener();
        getListView().setOnScrollListener(listener);
        plusAdapter = new PlusAdapter(getApplicationContext(), listener);
        setListAdapter(plusAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.google_plus:
                plusAdapter = new PlusAdapter(getApplicationContext(), listener);
                setListAdapter(plusAdapter);
                break;

            case R.id.google_now:
                nowAdapter = new NowAdapter(getApplicationContext(), listener);
                setListAdapter(nowAdapter);
                break;
        }
        return handled;
    }

}