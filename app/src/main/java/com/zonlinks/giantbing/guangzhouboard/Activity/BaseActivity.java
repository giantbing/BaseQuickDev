package com.zonlinks.giantbing.guangzhouboard.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zonlinks.giantbing.guangzhouboard.Util.Permission.PermissionReq;


/**
 * Created by giant on 2017/4/3.
 */

public abstract class BaseActivity extends Activity {
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected Runnable statnable;
    protected SharedPreferences prefs = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
  }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
