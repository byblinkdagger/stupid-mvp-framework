package com.oragee.groups.util;

import java.text.SimpleDateFormat;

/**
 * Created by lucky on 2018/1/24 0024
 * Here be dragon
 * 前方高能
 */

public class TimeUtil {

    /**
     * 时间字符转时间戳 -- S
     * @param date_str
     * @param format
     * @return
     */
    public static long date2TimeStamp(String date_str, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 取得当前时间戳 --（精确到秒）
     * @return
     */
    public static long getCurrentTime(){
        long time = System.currentTimeMillis();
        return time/1000;
    }

    /**
     * 格式化时间
     * @param dist
     * @return
     */
    public static String countTimeToString(Long dist) {

        int h = 0;
        int m = 0;

        String sh = "";
        String sm = "";

        m = (int) (dist % 3600 / 60);
        h = (int) (dist / 3600);

        if (h < 10) {
            sh = "0" + h;
        } else {
            sh = "" + h;
        }

        if (m < 10) {
            sm = "0" + m;
        } else {
            sm = "" + m;
        }

        return sh + "H:" + sm + "M";
    }

}
