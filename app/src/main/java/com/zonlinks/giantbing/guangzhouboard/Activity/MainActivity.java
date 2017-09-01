package com.zonlinks.giantbing.guangzhouboard.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zonlinks.giantbing.guangzhouboard.Adapter.MainPagerAdapter;
import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Entity.CheackUpdate;
import com.zonlinks.giantbing.guangzhouboard.HttpClient.HttpCilent;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Util.ToastHelper;
import com.zonlinks.giantbing.guangzhouboard.View.Pager.GallyPageTransformer;
import com.zonlinks.giantbing.guangzhouboard.View.Pager.MyViewPager;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoInnerLayout;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoTextInnerLayout;
import com.zonlinks.giantbing.guangzhouboard.View.PhotoVideoInnerLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private MainPagerAdapter mainPagerAdapter;
    private List<View> viewList;
    private PhotoInnerLayout photoInnerLayout ;
    private PhotoVideoInnerLayout photoVideoInnerLayout ;
    private PhotoTextInnerLayout photoTextInnerLayout ;
    @BindView(R.id.mainlayout)
    RelativeLayout mainlayout;
    @BindView(R.id.MainPager)
    ViewPager MainPager;




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
    }
    public void initData(){
        viewList = new ArrayList<>();
        photoInnerLayout = new PhotoInnerLayout(MainActivity.this,MainPager);
        photoVideoInnerLayout = new PhotoVideoInnerLayout(MainActivity.this,MainPager);
        photoTextInnerLayout = new PhotoTextInnerLayout(MainActivity.this,MainPager);
        viewList.add(photoInnerLayout);
        viewList.add(photoTextInnerLayout);
        viewList.add(photoVideoInnerLayout);
        mainPagerAdapter = new MainPagerAdapter(viewList);
        MainPager.setOffscreenPageLimit(3);
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

    private void checkUpdate(){

        HttpCilent.checkUpdate(new Callback<CheackUpdate>() {
            @Override
            public void onResponse(Call<CheackUpdate> call, Response<CheackUpdate> response) {
                CheackUpdate updateentity = response.body();
                Log.d("2333", "onResponse: "+updateentity.getUpdate());
                if (updateentity.getUpdate()==1){

                    getAlldata();
                }

            }

            @Override
            public void onFailure(Call<CheackUpdate> call, Throwable t) {

            }
        });
    }
    private void getAlldata(){
        HttpCilent.getAlldata(new Callback<AllData>() {
            @Override
            public void onResponse(Call<AllData> call, Response<AllData> response) {
                AllData allData =response.body();
                if (allData!=null){
                    if (allData.getMessage().equals("该设备为新设备")){
                        ToastHelper.info(MainActivity.this,"该设备为新设备，请移步后台设置！");
                    }else {
                        loadData(allData);
                    }
                }else {
                    ToastHelper.info(MainActivity.this,"该文化墙未设置，请移步后台设置！");
                }

            }

            @Override
            public void onFailure(Call<AllData> call, Throwable t) {
                ToastHelper.info(MainActivity.this,"该文化墙未设置，请移步后台设置！");
            }
        });
    }
    private void freashupdate(){
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
        statnable=null;
    }
    private void loadData(AllData alldata){
        viewList.clear();

        switch ( alldata.getDevice().getCultureWall().getPage1()){
            case "Image":
                viewList.add(photoInnerLayout);
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                break;
        }
        switch ( alldata.getDevice().getCultureWall().getPage2()){
            case "Image":
                viewList.add(photoInnerLayout);
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                break;
        }
        switch ( alldata.getDevice().getCultureWall().getPage3()){
            case "Image":
                viewList.add(photoInnerLayout);
                break;
            case "Image_Text":
                viewList.add(photoTextInnerLayout);
                break;
            case "Image_Video":
                viewList.add(photoVideoInnerLayout);
                break;
        }
        mainPagerAdapter.setViewlist(viewList);
        mainPagerAdapter.notifyDataSetChanged();
        MainPager.setAdapter(mainPagerAdapter);
    }
}
