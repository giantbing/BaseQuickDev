package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zonlinks.giantbing.guangzhouboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by P on 2017/8/30.
 */

public class PhotoVideoInnerLayout extends RelativeLayout {

    @BindView(R.id.videoinnerpager)
    ViewPager videoinnerpager;
    private ViewGroup viewGroup;
    private Context context;

//    public PhotoInnerLayout(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initview();
//       // splashContent.setText("2333333");
//    }

    public PhotoVideoInnerLayout(Context context, ViewGroup viewGroup) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
        initview();
    }


    private void initview() {
        //加载视图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.pager_photovideoinner, viewGroup, false);
        ButterKnife.bind(this, view);
        addView(view);
    }
    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */

}
