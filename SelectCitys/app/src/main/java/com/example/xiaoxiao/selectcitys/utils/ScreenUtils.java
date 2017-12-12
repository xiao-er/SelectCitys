package com.example.xiaoxiao.selectcitys.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * file name文件名：ScreenUtils
 * Description 描述：获得屏幕宽度
 * Created time 创建时间：2017/12/7 14:06
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class ScreenUtils {
    /**
     * 获得屏幕宽度
     *
     */
    public static int getScreenWidth(Context context) {
        if (context==null) {
            return 0;
        }
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        return width;
    }
}
