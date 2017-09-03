package com.zonlinks.giantbing.guangzhouboard.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by P on 2017/8/30.
 */

public class MainPagerAdapter extends PagerAdapter {
    private List<View> viewlist ;
    public MainPagerAdapter(List<View> viewlist) {
        this.viewlist = viewlist;
    }

    @Override
    public int getCount() {
        return viewlist == null ? 0 : viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewlist.get(position);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position<viewlist.size())
        container.removeView(viewlist.get(position));
    }
    public void setViewlist(List<View> viewlist) {
        this.viewlist = viewlist;
    }
}
