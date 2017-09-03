package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zonlinks.giantbing.guangzhouboard.Adapter.MainPagerAdapter;
import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Excute.MainPagerExcute;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.ToastHelper;

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
    private int currentIndex =0;
    private Handler handler;
    private MainPagerExcute ExcutLisnner;
    private int mainPosition;
//    public PhotoInnerLayout(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initview();
//       // splashContent.setText("2333333");
//    }
    Runnable cycleRunnable = new Runnable() {
        @Override
        public void run() {

                if (currentIndex>=viewList.size()){
                    currentIndex = 0;
                }
                photoinnerpager.setCurrentItem(currentIndex);
                currentIndex++;
                //递归循环，图片切换速度3秒一张

            handler.postDelayed(this, 3000);
        }
    };

    public int getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(int mainPosition) {
        this.mainPosition = mainPosition;
    }


    public void startCycle(){
        handler.post(cycleRunnable);
    }
    public void stopCycle(){
        handler.removeCallbacks(cycleRunnable);
    }
    public void freshView(){
        currentIndex = 0;
        photoinnerpager.setCurrentItem(currentIndex);
    }
    public PhotoInnerLayout(Context context, ViewGroup viewGroup, Handler handler,MainPagerExcute ExcutLisnner) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
        this.handler = handler;
        this.ExcutLisnner = ExcutLisnner;
        mainPosition = 0;
        initview();
    }

    public void loadData(List<AllData.SchoolCultureWallListBean> SchoolCultureWallList,int position){
        if (position==0){
            startCycle();
        }

        else{
            stopCycle();
        }

        mainPosition = position;
        viewList.clear();
        for (AllData.SchoolCultureWallListBean cultureWallListBean :SchoolCultureWallList){
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(C.BASEURL+cultureWallListBean.getImagePath())
                    .error(R.mipmap.image2)
                    .into(imageView);
            viewList.add(imageView);
            Log.d("2333", "loadData: "+C.BASEURL+cultureWallListBean.getImagePath());
        }
        mainPagerAdapter = new MainPagerAdapter(viewList);
        photoinnerpager.setAdapter(mainPagerAdapter);

    }
    private void initview() {
        //加载视图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.pager_photoinner, viewGroup, false);
        ButterKnife.bind(this, view);
        addView(view);
        viewList = new ArrayList<>();

        photoinnerpager.setOffscreenPageLimit(3);

        photoinnerpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==viewList.size()-1){
                    ToastHelper.success(context,"woyaoxiayiyela!");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ExcutLisnner.toNextPage(mainPosition+1);
                            freshView();
                            stopCycle();
                        }
                    },3000);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
