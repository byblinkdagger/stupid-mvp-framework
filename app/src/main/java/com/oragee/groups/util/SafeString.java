package com.oragee.groups.util;

/**
 * Created by lucky on 2017/11/10.
 * Here be dragons
 * 前方高能
 */

public class SafeString {

    public static String getString(String content){
        if (content != null){
            return content;
        }
        return "";
    }
}
