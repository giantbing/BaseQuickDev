package com.zonlinks.giantbing.guangzhouboard.View.Pager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by N on 2017/8/3.
 */

public class GallyPageTransformer implements ViewPager.PageTransformer {
    private static final float min_scale = 0.65f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor =Math.max(min_scale, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {

        } else if (position < 0) {
            //左边
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        } else if (position >= 0 && position < 1) {
           // 中间
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        } else if (position >= 1) {
            //右边
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        }
    }
}
