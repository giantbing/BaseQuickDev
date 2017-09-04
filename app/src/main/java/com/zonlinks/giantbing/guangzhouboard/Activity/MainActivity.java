package com.zonlinks.giantbing.guangzhouboard.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zonlinks.giantbing.guangzhouboard.Adapter.MainPagerAdapter;
import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Entity.CheackUpdate;
import com.zonlinks.giantbing.guangzhouboard.Excute.MainPagerExcute;
import com.zonlinks.giantbing.guangzhouboard.HttpClient.HttpCilent;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.ToastHelper;
import com.zonlinks.giantbing.guangzhouboard.View.MarqueeScrollTextView;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoInnerLayout;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoTextInnerLayout;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoVideoInnerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.margueeTextview)
    MarqueeScrollTextView margueeTextview;
    @BindView(R.id.mainlayout)
    RelativeLayout mainlayout;
    @BindView(R.id.MainPager)
    ViewPager MainPager;
    @BindView(R.id.schoole_name)
    TextView schooleName;
    @BindView(R.id.top_time)
    TextView topTime;
    @BindView(R.id.top_date)
    TextView topDate;
    @BindView(R.id.top_tempcture)
    TextView topTempcture;
    @BindView(R.id.top_air)
    TextView topAir;
    @BindView(R.id.top_changeskin)
    ImageView topChangeskin;
    private Runnable dateRunnable;
    private boolean cycleable = true;
    private MainPagerAdapter mainPagerAdapter;
    private List<View> viewList;
    private PhotoInnerLayout photoInnerLayout;
    private PhotoVideoInnerLayout photoVideoInnerLayout;
    private PhotoTextInnerLayout photoTextInnerLayout;
    private List<String> pageNumber;


//    @BindView(R.id.myView)
//    MyTestView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        freashupdate();
        getAlldata();
        initData();
        initView();
        initDateTime();
        hideSystemNavigationBar();
    }

    public void initData() {
        viewList = new ArrayList<>();
        pageNumber = new ArrayList<>();
        photoInnerLayout = new PhotoInnerLayout(MainActivity.this, MainPager, mHandler, new MainPagerExcute() {
            @Override
            public void toNextPage(int page) {
                toNextpage(page);
            }
        });
        photoVideoInnerLayout = new PhotoVideoInnerLayout(MainActivity.this, MainPager, mHandler, new MainPagerExcute() {
            @Override
            public void toNextPage(int page) {
                toNextpage(page);
            }
        });
        photoTextInnerLayout = new PhotoTextInnerLayout(MainActivity.this, MainPager, mHandler, new MainPagerExcute() {
            @Override
            public void toNextPage(int page) {
                toNextpage(page);
            }
        });
        mainPagerAdapter = new MainPagerAdapter(viewList);
        MainPager.setOffscreenPageLimit(3);
        MainPager.setAdapter(mainPagerAdapter);
        MainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    }

    private void checkUpdate() {

        HttpCilent.checkUpdate(new Callback<CheackUpdate>() {
            @Override
            public void onResponse(Call<CheackUpdate> call, Response<CheackUpdate> response) {
                CheackUpdate updateentity = response.body();
                Log.d("2333", "onResponse: " + updateentity.getUpdate());
                if (updateentity.getUpdate() == 1) {

                    getAlldata();
                }

            }

            @Override
            public void onFailure(Call<CheackUpdate> call, Throwable t) {

            }
        }, MainActivity.this);
    }

    private void getAlldata() {
        HttpCilent.getAlldata(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                AllData allData = response.body();
                if (allData != null) {
                    if (allData.getMessage().equals("该设备为新设备")) {
                        ToastHelper.info(MainActivity.this, "该设备为新设备，请移步后台设置！");
                    } else {
                        loadData(allData);
                        loadViewData(allData);
                    }
                } else {
                    ToastHelper.info(MainActivity.this, "该文化墙未设置，请移步后台设置！");
                }

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {
                ToastHelper.info(MainActivity.this, "该文化墙未设置，请移步后台设置！");
            }
        }, MainActivity.this);
    }

    private void freashupdate() {
        statnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // 在此处添加执行的代码
                //ToastHelper.success(MainActivity.this, "2333！", ToastHelper.LENGTH_SHORT, true);
                // PriseDialog.showDelDialog(MainActivity.this,"很强势！");
                checkUpdate();
                mHandler.postDelayed(this, C.REGFREASHITME);// 50ms后执行this，即runable
            }
        };
        mHandler.postDelayed(statnable, 0);// 打开定时器，50ms后执行runnable操作
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(statnable);
        statnable = null;
        photoInnerLayout.stopCycle();
        photoTextInnerLayout.stopCycle();
        photoVideoInnerLayout.stopCycle();
        mHandler.removeCallbacks(dateRunnable);
    }

    private void loadData(AllData alldata) {
        freshInitPage();
        viewList.clear();
        pageNumber.clear();
        switch (alldata.getDevice().getCultureWall().getPage1()) {
            case "Image":
                viewList.add(photoInnerLayout);
                pageNumber.add("Image");
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                pageNumber.add("Image_Text");
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                pageNumber.add("Image_Video");
                break;
        }
        switch (alldata.getDevice().getCultureWall().getPage2()) {
            case "Image":
                viewList.add(photoInnerLayout);
                pageNumber.add("Image");
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                pageNumber.add("Image_Text");
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                pageNumber.add("Image_Video");
                break;
        }
        switch (alldata.getDevice().getCultureWall().getPage3()) {
            case "Image":
                viewList.add(photoInnerLayout);
                pageNumber.add("Image");
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                pageNumber.add("Image_Text");
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                pageNumber.add("Image_Video");
                break;
        }

        mainPagerAdapter.setViewlist(viewList);
        mainPagerAdapter.notifyDataSetChanged();
        initPage(alldata, pageNumber);

    }

    public void toNextpage(int page) {

        if (page >= viewList.size()) {
            MainPager.setCurrentItem(0);
            setPageCycle(0);
        } else {
            MainPager.setCurrentItem(page);
            setPageCycle(page);
        }

    }


    private void setPageCycle(int page) {
        if (pageNumber.get(page).equals("Image")) {
            photoInnerLayout.startCycle();
            photoTextInnerLayout.stopCycle();
            photoVideoInnerLayout.stopCycle();
        } else if (pageNumber.get(page).equals("Image_Text")) {
            photoTextInnerLayout.startCycle();
            photoInnerLayout.stopCycle();
            photoVideoInnerLayout.stopCycle();
        } else if (pageNumber.get(page).equals("Image_Video")) {
            photoVideoInnerLayout.startCycle();
            photoInnerLayout.stopCycle();
            photoTextInnerLayout.stopCycle();

        }
    }

    public void initPage(AllData alldata, List<String> list) {
        for (int position = 0; position < list.size(); position++) {
            String s = list.get(position);
            if (s.equals("Image")) {
                photoInnerLayout.loadData(alldata.getSchoolCultureWallList(), position);
            } else if (s.equals("Image_Text")) {
                photoTextInnerLayout.loadData(alldata.getSchoolCultureWallList(), alldata.getSchoolCultureWallTextList(), position);
            } else if (s.equals("Image_Video")) {
                photoVideoInnerLayout.loadData(alldata.getSchoolCultureWallList(), alldata.getSchoolVideoCultureWallList(), position);
            }


        }
    }

    public void freshInitPage() {
        photoInnerLayout.stopCycle();
        photoTextInnerLayout.stopCycle();
        photoVideoInnerLayout.stopCycle();
        photoInnerLayout.freshView();
        photoTextInnerLayout.freshView();
        photoVideoInnerLayout.freshView();
    }

    public void initView() {
        margueeTextview.setSpeed(2);
        margueeTextview.setCurrentPosition(1920);

    }
    public void loadViewData(AllData allData){
        schooleName.setText(allData.getDevice().getSchool().getName());
        topTempcture.setText(allData.getWeather().getResult().getData().getRealtime().getWeather().getTemperature()+"°C\t"
                +allData.getWeather().getResult().getData().getRealtime().getWeather().getInfo());
        topAir.setText("空气："+allData.getWeather().getResult().getData().getPm25().getPm25().getQuality());
        margueeTextview.setText(allData.getSchoolButtomNotifyList().get(0).getContent());
    }
    public void initDateTime(){

        dateRunnable = new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat timeformat=new SimpleDateFormat("HH:mm");
                SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                String time=timeformat.format(date);
                String datestring=dateformat.format(date);
                topDate.setText(datestring);
                topTime.setText(time);
                mHandler.postDelayed(this,60000);
            }
        };
        mHandler.post(dateRunnable);
    }
    private void hideSystemNavigationBar() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = this.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


}
