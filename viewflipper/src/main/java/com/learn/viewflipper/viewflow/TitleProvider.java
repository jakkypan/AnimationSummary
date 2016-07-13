/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.learn.viewflipper.viewflow;

public interface TitleProvider {

    /**
     * Returns the title of the view at position
     * @param position
     * @return
     */
    String getTitle(int position);
}
