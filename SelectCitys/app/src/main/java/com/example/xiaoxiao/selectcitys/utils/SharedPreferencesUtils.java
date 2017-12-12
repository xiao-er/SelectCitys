package com.example.xiaoxiao.selectcitys.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * file name文件名：SharedPreferencesUtils
 * Description 描述：
 * Created time 创建时间：2017/12/8 16:04
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class SharedPreferencesUtils {
    //最近访问城市国内
    public static final String KEY_RECENT_CITYS_ONE = "recent_citys_one";
    public static final String KEY_RECENT_CITYS_TWO = "recent_citys_two";
    public static final String KEY_RECENT_CITYS_THREE = "recent_citys_three";
    public static final String KEY_RECENT_CITYS_FOUR = "recent_citys_four";
    public static final String KEY_RECENT_CITYS_FIVE = "recent_citys_five";
    public static final String KEY_RECENT_CITYS_SIX = "recent_citys_six";


    private static SharedPreferences preferences;

    public static SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences("AAZJ", Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public static String getRecentCity(Context context, int num) {
        switch (num) {
            case 0:
                return getPreferences(context).getString(KEY_RECENT_CITYS_ONE, null);
            case 1:
                return getPreferences(context).getString(KEY_RECENT_CITYS_TWO, null);
            case 2:
                return getPreferences(context).getString(KEY_RECENT_CITYS_THREE, null);
            case 3:
                return getPreferences(context).getString(KEY_RECENT_CITYS_FOUR, null);
            case 4:
                return getPreferences(context).getString(KEY_RECENT_CITYS_FIVE, null);
            case 5:
                return getPreferences(context).getString(KEY_RECENT_CITYS_SIX, null);
            default:
                return null;
        }
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void cacheRecentCitys(Context context, List<String> recentCitys) {
        SharedPreferences.Editor editor = getEditor(context);
        switch (recentCitys.size()) {
            case 0:
                break;
            case 1:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.commit();
                break;
            case 2:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.commit();
                break;
            case 3:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.putString(KEY_RECENT_CITYS_THREE, recentCitys.get(2));
                editor.commit();
                break;
            case 4:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.putString(KEY_RECENT_CITYS_THREE, recentCitys.get(2));
                editor.putString(KEY_RECENT_CITYS_FOUR, recentCitys.get(3));
                editor.commit();
                break;
            case 5:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.putString(KEY_RECENT_CITYS_THREE, recentCitys.get(2));
                editor.putString(KEY_RECENT_CITYS_FOUR, recentCitys.get(3));
                editor.putString(KEY_RECENT_CITYS_FIVE, recentCitys.get(4));
                editor.commit();
                break;
            case 6:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.putString(KEY_RECENT_CITYS_THREE, recentCitys.get(2));
                editor.putString(KEY_RECENT_CITYS_FOUR, recentCitys.get(3));
                editor.putString(KEY_RECENT_CITYS_FIVE, recentCitys.get(4));
                editor.putString(KEY_RECENT_CITYS_SIX, recentCitys.get(5));
                editor.commit();
                break;
            default:
                editor.putString(KEY_RECENT_CITYS_ONE, recentCitys.get(0));
                editor.putString(KEY_RECENT_CITYS_TWO, recentCitys.get(1));
                editor.putString(KEY_RECENT_CITYS_THREE, recentCitys.get(2));
                editor.putString(KEY_RECENT_CITYS_FOUR, recentCitys.get(3));
                editor.putString(KEY_RECENT_CITYS_FIVE, recentCitys.get(4));
                editor.putString(KEY_RECENT_CITYS_SIX, recentCitys.get(5));
                editor.commit();
                break;
        }
    }




}
