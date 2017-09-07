package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by P on 2017/9/5.
 */

public class NotToouchPager extends ViewPager {
    public NotToouchPager(Context context) {
        super(context);
    }

    public NotToouchPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return false;
    }
}
