package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import static com.zonlinks.giantbing.guangzhouboard.C.INNERPAGERFREASHITME;
import static com.zonlinks.giantbing.guangzhouboard.C.MAINPAGERFREASHITME;

/**
 * Created by P on 2017/8/30.
 */

public class PhotoTextInnerLayout extends RelativeLayout {

    @BindView(R.id.textinnerPager)
    NotToouchPager textinnerPager;
    @BindView(R.id.text_pager_title)
    TextView textPagerTitle;
    @BindView(R.id.text_pager_content)
    VerticalScrollTextView textPagerContent;
    @BindView(R.id.text_pager_time)
    TextView textPagerTime;
    private MainPagerAdapter mainPagerAdapter;
    private List<View> viewList;
    private ViewGroup viewGroup;
    private Context context;
    private int currentIndex = 0;
    private Handler handler;
    private MainPagerExcute ExcutLisnner;
    private int mainPosition;
    private List<AllData.SchoolCultureWallTextListBean> cultureWallTextListBeen;
    //    public PhotoInnerLayout(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        initview();
//       // splashContent.setText("2333333");
//    }
    Runnable cycleRunnable = new Runnable() {
        @Override
        public void run() {

            if (currentIndex >= viewList.size()) {
                currentIndex = 0;
            }
            textinnerPager.setCurrentItem(currentIndex);
            currentIndex++;
            //递归循环，图片切换速度3秒一张
            handler.postDelayed(this, INNERPAGERFREASHITME);

        }
    };

    public int getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(int mainPosition) {
        this.mainPosition = mainPosition;
    }

    public void startCycle() {
        handler.post(cycleRunnable);
        initText(cultureWallTextListBeen);
        if(viewList.size()==1){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ExcutLisnner.toNextPage(mainPosition+1);
                    freshView();
                    stopCycle();
                }
            },MAINPAGERFREASHITME);
        }
    }

    public void stopCycle() {
        handler.removeCallbacks(cycleRunnable);
        textPagerContent.setText("");
    }

    public void freshView() {
        currentIndex = 0;
        textinnerPager.setCurrentItem(currentIndex);
        textPagerTitle.setText("");
        textPagerTime.setText("");
        textPagerContent.setText("后台未设置！");
    }

    public PhotoTextInnerLayout(Context context, ViewGroup viewGroup, Handler handler, MainPagerExcute ExcutLisnner) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
        this.handler = handler;
        this.ExcutLisnner = ExcutLisnner;
        mainPosition = 0;
        initview();
    }

    public void loadData(List<AllData.SchoolCultureWallListBean> SchoolCultureWallList, List<AllData.SchoolCultureWallTextListBean> cultureWallTextListBeen, int position) {
       this.cultureWallTextListBeen = cultureWallTextListBeen;
        if (position == 0)
            startCycle();
        else
            stopCycle();
        mainPosition = position;
        viewList.clear();
        for (AllData.SchoolCultureWallListBean cultureWallListBean : SchoolCultureWallList) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(C.BASEURL + cultureWallListBean.getImagePath())
                    .error(R.mipmap.image2)
                    .into(imageView);
            viewList.add(imageView);

        }
        mainPagerAdapter = new MainPagerAdapter(viewList);
        textinnerPager.setAdapter(mainPagerAdapter);

    }

    private void initview() {
        //加载视图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.pager_phototext, viewGroup, false);
        ButterKnife.bind(this, view);
        addView(view);
        viewList = new ArrayList<>();

        textinnerPager.setOffscreenPageLimit(3);

        textinnerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == viewList.size() - 1) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ExcutLisnner.toNextPage(mainPosition + 1);
                            freshView();
                            stopCycle();
                        }
                    }, MAINPAGERFREASHITME);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void initText(List<AllData.SchoolCultureWallTextListBean> cultureWallTextListBeen){

        if (cultureWallTextListBeen!=null){
            if (cultureWallTextListBeen.size()>0){
                textPagerTitle.setText(cultureWallTextListBeen.get(0).getTitle());
                textPagerTime.setText(cultureWallTextListBeen.get(0).getCreateTime());
                textPagerContent.setText(cultureWallTextListBeen.get(0).getContent());
            }
        }


    }
    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */


}
