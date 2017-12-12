package com.example.xiaoxiao.selectcitys.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * file name文件名：OnItemClickListener
 * Description 描述：通用的RecyclerView 的ItemClickListener，包含点击和长按
 * Created time 创建时间：2017/12/8 15:46
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T t, int position);

    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
