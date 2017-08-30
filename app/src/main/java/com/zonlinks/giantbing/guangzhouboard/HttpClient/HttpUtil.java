package com.zonlinks.giantbing.guangzhouboard.HttpClient;

import android.util.Log;

import com.zonlinks.giantbing.guangzhouboard.Util.Decode;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;


/**
 * Created by P on 2017/8/4.
 */

public class HttpUtil {

    public static String createNormalSign(String[] params){
        Arrays.sort(params);
        StringBuffer content = new StringBuffer();
        for(String str:params){
            content.append(str);
        }
        return Decode.SHA1(content.toString());
    }
    public static String create_nonce_str() {
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
//
//        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTimestamp() {
        return  ""+new Date().getTime()/1000;
    }
    public static  boolean ping(String Url) {

        String result = null;

        try {

            String ip =Url;// 除非百度挂了，否则用这个应该没问题~

            Process p = Runtime.getRuntime().exec("ping -c 1 -w 1 " + ip);//ping3次


            int status = p.waitFor();

            if (status == 0) {

                result = "successful~";

                return true;

            } else {

                result = "failed~ cannot reach the IP address";

            }

        } catch (IOException e) {

            result = "failed~ IOException";

        } catch (InterruptedException e) {

            result = "failed~ InterruptedException";

        } finally {

            Log.i("TTT", "result = " + result);

        }

        return false;

    }

    public static String code2String(int code){

        switch (code){
            case 10027:
                return "无效设备Mac或学段";

            case 10021:
                return "密码错误！";
            case 10010:
                return "系统异常";
            case 10019:
                return "用户名或学段错误";
            case 10030:
                return "MAC地址与账号不匹配";
            case 10028:
                return "没有权限";



            default:
                return "发生错误，错误码为"+code;
        }

    }
}
