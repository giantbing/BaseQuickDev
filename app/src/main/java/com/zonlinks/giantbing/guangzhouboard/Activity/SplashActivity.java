package com.zonlinks.giantbing.guangzhouboard.Activity;

import android.os.Bundle;
import android.util.Log;

import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.CheackUpdate;
import com.zonlinks.giantbing.guangzhouboard.HttpClient.HttpCilent;
import com.zonlinks.giantbing.guangzhouboard.R;
import com.zonlinks.giantbing.guangzhouboard.Entity.RegEntity;
import com.zonlinks.giantbing.guangzhouboard.Util.AnimotionHelper;
import com.zonlinks.giantbing.guangzhouboard.Util.StartActivityHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getRegdit();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
    private void getRegdit(){
        HttpCilent.regDevice(new Callback<RegEntity>() {
            @Override
            public void onResponse(Call<RegEntity> call, Response<RegEntity> response) {
                if (response!=null){
                    getDevice();
                }

            }

            @Override
            public void onFailure(Call<RegEntity> call, Throwable t) {
                Log.d("2333", "onFailure: "+t.toString());
            }
        },SplashActivity.this);
    }
    private void delayToMain(){

                StartActivityHelper.startMainActivityTraslate(SplashActivity.this, AnimotionHelper.slidebundle(SplashActivity.this));
                finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(statnable);
        statnable=null;
    }
    private void getDevice(){
        statnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // 在此处添加执行的代码
                //ToastHelper.success(MainActivity.this, "2333！", ToastHelper.LENGTH_SHORT, true);
                // PriseDialog.showDelDialog(MainActivity.this,"很强势！");
                delayToMain();
                mHandler.postDelayed(this, C.REGFREASHITME);// 50ms后执行this，即runable
            }
        };
        mHandler.postDelayed(statnable, 0);// 打开定时器，50ms后执行runnable操作
    }

}
