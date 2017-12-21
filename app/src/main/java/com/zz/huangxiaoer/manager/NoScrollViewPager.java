package com.zz.huangxiaoer.manager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 韩永光
 * on 2017/11/17 10:51.
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//true表示触摸无响应
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//false
    }
}
