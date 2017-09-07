package com.zonlinks.giantbing.guangzhouboard.Util;


import com.zonlinks.giantbing.guangzhouboard.BuildConfig;

/**
 * Created by P on 2017/9/7.
 */

public class LogUtil {
    private static final String TAG = "2333";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void i( String msg) {
        if (DEBUG)
            android.util.Log.i(TAG, msg);
    }

    public static void e( String msg) {
        if (DEBUG)
            android.util.Log.e(TAG, msg);
    }

    public static void d( String msg) {
        if (DEBUG)
            android.util.Log.d(TAG, msg);
    }

    public static void v( String msg) {
        if (DEBUG)
            android.util.Log.v(TAG, msg);
    }

    public static void w( String msg) {
        if (DEBUG)
            android.util.Log.w(TAG, msg);
    }

}
