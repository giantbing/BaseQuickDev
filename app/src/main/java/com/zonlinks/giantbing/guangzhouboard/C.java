package com.zonlinks.giantbing.guangzhouboard;

import android.os.Environment;

import com.zonlinks.giantbing.guangzhouboard.Util.MacUtil;


/**
 * Created by giant on 2017/7/7.
 */

public class C {

    //自定义变量
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final String SCHOOLNAME = "广州市珠海区滨江东路小学";
    public static final String GRADE = "grade";

    public static final String BaseURLKEY = "BASEURL";

    public static final String USERNAME = "USERNAME";
    public static final String USERPWD = "USERPWD";
    public static final String ISDEVICE = "ISDEVICE";
    public static final String TAG = "2333";
    private static boolean istest = false;
    //文件相关
    public static String IMGSAVEPATH =Environment.getExternalStorageDirectory().toString() + "/zonglinks/img/";
    public static String CacheSAVEPATH =Environment.getExternalStorageDirectory().toString() + "/zonglinks/img/cache/";
    public static String ASKCacheSAVEPATH =Environment.getExternalStorageDirectory().toString() + "/zonglinks/img/cache/ask";

    public static String BASEURL="http://114.55.173.248:2556/";

    public static final String Brodcasturl = "rtsp://%s:8554/classlive_%s.sdp";

    public static String NANPINGBASEURL="http://www.xszhxy.com/com/bennet/webapp/api/";
    public static String APPKEY="zonlinks_intClass";
    public static String APPSECRET="74de35ab402940b199b44b043291f4ad";
    //数据15秒刷新一次
    public static int STATEFREASHITME=15000;
    public static int REGFREASHITME=5000;
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
