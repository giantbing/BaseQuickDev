package com.zonlinks.giantbing.guangzhouboard.View;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;
import com.zonlinks.giantbing.guangzhouboard.Adapter.MainPagerAdapter;
import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Excute.MainPagerExcute;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.ToastHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zonlinks.giantbing.guangzhouboard.C.CacheSAVEPATH;
import static com.zonlinks.giantbing.guangzhouboard.C.INNERPAGERFREASHITME;
import static com.zonlinks.giantbing.guangzhouboard.C.MAINPAGERFREASHITME;
import static com.zonlinks.giantbing.guangzhouboard.C.VIDEOPAGERFREASHITME;

/**
 * Created by P on 2017/8/30.
 */

public class PhotoVideoInnerLayout extends RelativeLayout {

    @BindView(R.id.videoinnerpager)
    NotToouchPager videoinnerpager;
    @BindView(R.id.videoplayer)
    ListGSYVideoPlayer videoplayer;
    private MainPagerAdapter mainPagerAdapter;
    private List<View> viewList;
    private ViewGroup viewGroup;
    private Context context;
    private int currentIndex = 0;
    private Handler handler;
    private MainPagerExcute ExcutLisnner;
    private int mainPosition;
    private List<AllData.SchoolVideoCultureWallListBean> videoCultureWallListBeen ;
    private File cachefile;
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
            videoinnerpager.setCurrentItem(currentIndex);
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
        startPlay();
    }

    public void stopCycle() {
        handler.removeCallbacks(cycleRunnable);
    }

    public void freshView() {
        currentIndex = 0;
        videoinnerpager.setCurrentItem(currentIndex);
    }

    public PhotoVideoInnerLayout(Context context, ViewGroup viewGroup, Handler handler, MainPagerExcute ExcutLisnner) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
        this.handler = handler;
        this.ExcutLisnner = ExcutLisnner;
        cachefile = new File(CacheSAVEPATH);
        mainPosition = 0;
        initview();
    }

    public void loadData(List<AllData.SchoolCultureWallListBean> SchoolCultureWallList, List<AllData.SchoolVideoCultureWallListBean> videoCultureWallListBeen, int position) {
       this.videoCultureWallListBeen = videoCultureWallListBeen;

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
        videoinnerpager.setAdapter(mainPagerAdapter);
        if (position == 0)
            startCycle();
        else
            stopCycle();
    }

    private void initview() {
        //加载视图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.pager_photovideoinner, viewGroup, false);
        ButterKnife.bind(this, view);
        addView(view);
        viewList = new ArrayList<>();
        videoinnerpager.setOffscreenPageLimit(3);



    }
    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    public void startPlay(){

        if (videoCultureWallListBeen!=null){
            List<GSYVideoModel> videolist = new ArrayList<>();
            for(AllData.SchoolVideoCultureWallListBean bean:videoCultureWallListBeen){
                GSYVideoModel gsyVideoModel = new GSYVideoModel(bean.getImagePath(),bean.getTitle());
                videolist.add(gsyVideoModel);
                Log.d("2333", "startPlay: "+bean.getImagePath());
            }
            if (videolist.size()>0){

                videoplayer.setUp(videolist,false,0,cachefile);
                videoplayer.getBackButton().setVisibility(View.GONE);
                videoplayer.setIsTouchWiget(true);
                videoplayer.startPlayLogic();
                videoplayer.setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {

                    }

                    @Override
                    public void onClickStartIcon(String url, Object... objects) {

                    }

                    @Override
                    public void onClickStartError(String url, Object... objects) {

                    }

                    @Override
                    public void onClickStop(String url, Object... objects) {

                    }

                    @Override
                    public void onClickStopFullscreen(String url, Object... objects) {

                    }

                    @Override
                    public void onClickResume(String url, Object... objects) {

                    }

                    @Override
                    public void onClickResumeFullscreen(String url, Object... objects) {

                    }

                    @Override
                    public void onClickSeekbar(String url, Object... objects) {

                    }

                    @Override
                    public void onClickSeekbarFullscreen(String url, Object... objects) {

                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        // TODO: 2017/9/4
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ExcutLisnner.toNextPage(mainPosition + 1);
                                freshView();
                                stopCycle();
                            }
                        }, VIDEOPAGERFREASHITME);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {

                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {

                    }

                    @Override
                    public void onQuitSmallWidget(String url, Object... objects) {

                    }

                    @Override
                    public void onEnterSmallWidget(String url, Object... objects) {

                    }

                    @Override
                    public void onTouchScreenSeekVolume(String url, Object... objects) {

                    }

                    @Override
                    public void onTouchScreenSeekPosition(String url, Object... objects) {

                    }

                    @Override
                    public void onTouchScreenSeekLight(String url, Object... objects) {

                    }

                    @Override
                    public void onPlayError(String url, Object... objects) {
                        // gsyVideoPlayer.setVisibility(View.GONE);
                        ToastHelper.error(context,"视屏地址失效！");
                        videoinnerpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

                    @Override
                    public void onClickStartThumb(String url, Object... objects) {

                    }

                    @Override
                    public void onClickBlank(String url, Object... objects) {

                    }

                    @Override
                    public void onClickBlankFullscreen(String url, Object... objects) {

                    }
                });
            }else {
                ToastHelper.error(context,"视屏地址未添加！");
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
                videoinnerpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        }


    }
}
