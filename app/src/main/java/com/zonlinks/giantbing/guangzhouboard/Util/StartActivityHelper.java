package com.zonlinks.giantbing.guangzhouboard.Util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import classroom.zonlinks.com.classroom.Activity.MainActivity;
import classroom.zonlinks.com.classroom.Activity.Splash_activity;

/*
 * Created by giant on 2017/3/17.
 */

public class StartActivityHelper {
    public StartActivityHelper() {
     }
//    public static void startsecendActivityTraslate(Context context , Bundle bundle){
//        Intent intent = new Intent(context, SecondActivity.class) ;
//        context.startActivity(intent, bundle);
//    }
    public static void startMainActivityTraslate(Context context , Bundle bundle){
        Intent intent = new Intent(context, MainActivity.class) ;
        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent, bundle);
    }
    public static void startSplashTraslate(Context context , Bundle bundle){
        Intent intent = new Intent(context, Splash_activity.class) ;
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent, bundle);
    }

}
