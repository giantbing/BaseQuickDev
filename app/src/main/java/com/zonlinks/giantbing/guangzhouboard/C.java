package com.zonlinks.giantbing.guangzhouboard;

import android.os.Environment;

import com.zonlinks.giantbing.guangzhouboard.Util.MacUtil;


/**
 * Created by giant on 2017/7/7.
 */

public class C {

    //自定义变量
    public static final int TAKE_PHOTO = 1;
    private static boolean istest = false;
    //文件相关
    public static String IMGSAVEPATH =Environment.getExternalStorageDirectory().toString() + "/zonglinks/img/";
    public static String CacheSAVEPATH =Environment.getExternalStorageDirectory().toString() + "/zonglinks/img/cache/";

    public static String BASEURL="http://114.55.173.248:2556/";

    //数据15秒刷新一次
    public static int STATEFREASHITME=15000;
    public static int REGFREASHITME=5000;
    //主页面切换
    public static int MAINPAGERFREASHITME=5000;
    //子页面切换
    public static int INNERPAGERFREASHITME=5000;
    //视屏播放完毕切换时间
    public static int VIDEOPAGERFREASHITME=1000;
    public static String MAC ;

    public static String getMAC() {
        if (istest){
            MAC = "00:08:22:6e:c8:fb";
        }else {
            MAC = MacUtil.getMac();
        }
        return MAC;
    }

    public static String WECHATURL = "https://api.weixin.qq.com/";
    public static String TESTPICURL = "http://wx1.sinaimg.cn/";
}
