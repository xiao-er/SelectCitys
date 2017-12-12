package com.example.xiaoxiao.selectcitys.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * file name文件名：StringUtils
 * Description 描述：
 * Created time 创建时间：2017/12/8 16:02
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class StringUtils {


    //判断字符串是否为空
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }


}
