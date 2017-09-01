package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zonlinks.giantbing.guangzhouboard.Adapter.MainPagerAdapter;
import com.zonlinks.giantbing.guangzhouboard.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by P on 2017/8/30.
 */

public class PhotoInnerLayout extends RelativeLayout {

    @BindView(R.id.photoinnerpager)
    ViewPager photoinnerpager;
    private MainPagerAdapter mainPagerAdapter;
    private List<View> viewList;
    private ViewGroup viewGroup;
    private Context context;

//    public PhotoInnerLayout(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initview();
//       // splashContent.setText("2333333");
//    }

    public PhotoInnerLayout(Context context, ViewGroup viewGroup) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
        initview();
    }


    private void initview() {
        //加载视图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.pager_photoinner, viewGroup, false);
        ButterKnife.bind(this, view);
        addView(view);
        viewList = new ArrayList<>();

        photoinnerpager.setOffscreenPageLimit(3);
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.image1);
            viewList.add(imageView);
        }
        mainPagerAdapter = new MainPagerAdapter(viewList);
        //pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
//        ViewGroup.LayoutParams lp = MainPager.getLayoutParams();
//        if (lp == null) {
//            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
//        } else {
//            lp.width = pagerWidth;
//        }
        // MainPager.setLayoutParams(lp);
        // MainPager.setPageMargin(-50);

        // MainPager.setPageTransformer(true, new GallyPageTransformer());
        photoinnerpager.setAdapter(mainPagerAdapter);
    }
    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */

}
