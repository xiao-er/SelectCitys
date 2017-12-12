package com.example.xiaoxiao.selectcitys.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xiaoxiao.selectcitys.R;
import com.example.xiaoxiao.selectcitys.utils.DensityUtil;
import com.example.xiaoxiao.selectcitys.utils.ScreenUtils;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

/**
 * file name文件名：ScrollNavigationCursorView
 * Description 描述：带有游标得滚动导航
 * Created time 创建时间：2017/12/7 14:03
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class ScrollNavigationCursorView extends RelativeLayout implements ScrollNavigationView.OnNavigationItemListener {
    private Context context;
    private int cursor_width = 4;// 游标默认宽度
    private int screen_width;
    private ScrollNavigationView scrollNavigationView;
    private boolean is_boarder_top_line = false;// 是否有顶部边界
    private boolean is_boarder_bottom_line = false;// 是否有底部边界
    private ImageView cursor;
    private View v_top_line;
    private View v_bottom_line;

    private int bmpw;
    private int offset;

    private OnViewPagerChange onViewPagerChange;

    public ScrollNavigationCursorView(Context context) {
        super(context);
        initView(context);
    }

    public interface OnViewPagerChange {
        // ViewPager变更时的回调
        public void onFocusChange(int position);
    }

    public ScrollNavigationCursorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollNavigationCursorView(Context context, AttributeSet attrs,
                                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 设置要显示的tab
     */
    public void setSelect(int id) {
        scrollNavigationView.setSelector(id);
    }

    public void setCursorBg(int resId) {
        cursor.setImageResource(resId);


    }

    public void setViewPager(ViewPager viewPager) {
        scrollNavigationView.setViewPager(viewPager);
    }

    /**
     * 设置要显示的tab
     *
     * @param position tab所在的位置
     */
    public void setViewPageShowIndex(int position) {
        scrollNavigationView.setViewPageShowIndex(position);
    }

    public void initItems(List<String> data) {
        scrollNavigationView.initItems(data);
        initCursorPos(context);
        if (is_boarder_top_line) {
            v_top_line.setVisibility(View.VISIBLE);
        }
        if (is_boarder_bottom_line) {
            v_bottom_line.setVisibility(View.VISIBLE);
        }
    }

    public int getTab_count() {
        return scrollNavigationView.getTab_count();
    }

    public boolean isHas_split() {
        return scrollNavigationView.isHas_split();
    }

    public void setHas_split(boolean has_split) {
        scrollNavigationView.setHas_split(has_split);
    }

    public int getSplit_width() {
        return scrollNavigationView.getSplit_width();
    }

    public void setSplit_width(int split_width) {
        scrollNavigationView.setSplit_width(split_width);
    }

    public int getScrollViewHeight() {
        return scrollNavigationView.getScrollViewHeight();
    }

    public void setScrollViewHeight(int height) {
        scrollNavigationView.setScrollViewHeight(height);
    }

    public void setSelectBg(int select_bg_color) {
        scrollNavigationView.setSelectBg(select_bg_color);
    }

    public void setDefultBg(int defult_bg_color) {
        scrollNavigationView.setDefultBg(defult_bg_color);
    }

    public void setSelect_text_color(int select_text_color) {
        scrollNavigationView.setSelect_text_color(select_text_color);
    }

    public void setDefult_text_color(int defult_text_color) {
        scrollNavigationView.setDefult_text_color(defult_text_color);
    }

//	/**
//	 * 设置默认背景色
//	 */
//	public void setDefultBg(int resId){
//		scrollNavigationView.setDefultBg(resId);
//	}
//
//	/**
//	 * 设置选中焦点背景色
//	 */
//	public void setSelectBg(int resId){
//		scrollNavigationView.setSelectBg(resId);
//	}


    /**
     * 设置每屏幕显示tab的个数
     */
    public void setTab_count(int tab_count) {
        scrollNavigationView.setTab_count(tab_count);
    }

    public void setOnViewPagerChange(OnViewPagerChange onViewPagerChange) {
        this.onViewPagerChange = onViewPagerChange;
    }

    public boolean isIs_boarder_top_line() {
        return is_boarder_top_line;
    }

    public void setIs_boarder_top_line(boolean is_boarder_top_line) {
        this.is_boarder_top_line = is_boarder_top_line;
    }

    public boolean isIs_boarder_bottom_line() {
        return is_boarder_bottom_line;
    }

    public void setIs_boarder_bottom_line(boolean is_boarder_bottom_line) {
        this.is_boarder_bottom_line = is_boarder_bottom_line;
    }

    public float getText_size() {
        return scrollNavigationView.getText_size();
    }

    public void setText_size(int size) {
        scrollNavigationView.setText_size(size);
    }

    public int getBarPaddingLeft() {
        return scrollNavigationView.getBarPaddingLeft();
    }

    public void setBarPaddingLeft(int barPaddingLeft) {
        scrollNavigationView.setBarPaddingLeft(barPaddingLeft);
    }

    private void initView(Context context) {
        this.context = context;
        screen_width = ScreenUtils.getScreenWidth(context);
        View view = LayoutInflater.from(context).inflate(R.layout.scroll_navigation_cursor_layout, null);

        scrollNavigationView = (ScrollNavigationView) view.findViewById(R.id.scrollNavigationView);
        scrollNavigationView.setOnNavigationItemClickListener(this);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		scrollNavigationView.setLayoutParams(params);

        cursor = (ImageView) view.findViewById(R.id.cursor);
        cursor.setBackgroundColor(getResources().getColor(R.color.colorYellow));//游标选中时线条颜色
        cursor.setVisibility(View.GONE);


        v_top_line = view.findViewById(R.id.v_top_line);
        v_bottom_line = view.findViewById(R.id.v_bottom_line);

        addView(view);
    }

    // 初始化指示器位置
    private void initCursorPos(Context context) {
        LayoutParams lp = (LayoutParams) cursor.getLayoutParams();
        cursor_width = DensityUtil.dip2px(getContext(), 1);
        lp.height = cursor_width;
//        lp.height = 6;
        lp.width = (screen_width - getBarPaddingLeft()) / getTab_count();
        cursor.setLayoutParams(lp);

        cursor.setVisibility(View.VISIBLE);
        bmpw = (screen_width - getBarPaddingLeft()) / getTab_count();
        offset = ((screen_width - getBarPaddingLeft()) / getTab_count() - bmpw) / 2; // 计算偏移量

        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, getHeight());
        cursor.setImageMatrix(matrix); // 设置动画初始位置
    }

    /**
     * @param x 被选中项的X轴坐标
     * @Description
     * @author zyb
     * @date 2015-9-22 上午11:51:27
     */
    @SuppressLint("NewApi")
    private void setCursorPosition(int x) {
        int position = x;
        //将cursor移动到当前选中tab的x轴坐标上
        ObjectAnimator.ofFloat(cursor, "TranslationX", position).setDuration(0).start();
    }


    @Override
    public void onNavigationMove(int x) {
        setCursorPosition(x);
    }

    @Override
    public void setmyTranslationX(float x) {
        ViewHelper.setTranslationX(cursor, x);
    }

    @Override
    public void onFocusChange(int position) {
        if (onViewPagerChange != null) {
            onViewPagerChange.onFocusChange(position);
        }
    }

}