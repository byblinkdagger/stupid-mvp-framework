package com.oragee.groups.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Doggie
 * @Copyright com.taiyiche
 * @description 实现的主要功能:
 * @date 2016/11/22
 */

public class PhoneUtil {

    /**
     * 检查是不是手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhoneNumber(String mobiles) {

        Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[135678]|18[0-9]|14[579])[0-9]{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }

    /**
     * 检查是不是包含手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean hasPhoneNumber(String mobiles) {

        Pattern p = Pattern.compile("(0|86|17951)?(13[0-9]|15[012356789]|17[135678]|18[0-9]|14[579])[0-9]{8}");

        Matcher m = p.matcher(mobiles);

        return m.find();
    }

    /**
     * 检查是不是包含座机号
     *
     * @param mobiles
     * @return
     */
    public static boolean hasLandlineNumber(String mobiles) {

        Pattern p = Pattern.compile("0[0-9]{2,3}-[0-9]{8}");

        Matcher m = p.matcher(mobiles);

        return m.find();
    }

    /**
     * 筛选出手机号
     *
     * @param mobiles
     * @return
     */
    public static String pickPhoneNumber(String mobiles) {

        Pattern p = Pattern.compile("(0|86|17951)?(13[0-9]|15[012356789]|17[135678]|18[0-9]|14[579])[0-9]{8}");

        Matcher m = p.matcher(mobiles);

        if (m.find())
            return m.group();
        else {
            return "";
        }
    }

    /**
     * 筛选出座机号
     *
     * @param mobiles
     * @return
     */
    public static String pickLandlineNumber(String mobiles) {

        Pattern p = Pattern.compile("0[0-9]{2,3}-[0-9]{8}");

        Matcher m = p.matcher(mobiles);

        if (m.find())
            return m.group();
        else {
            return "";
        }
    }
}
