package com.zonlinks.giantbing.guangzhouboard.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by P on 2017/8/9.
 */

public class DateUtil {

    /**
     * 得到几天前的时间
     *
     * @param day
     * @return date
     */
    public static Date getDateBefore(int day){
        Date dNow = new Date();
        Calendar now =Calendar.getInstance();
        now.setTime(dNow);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }
}
