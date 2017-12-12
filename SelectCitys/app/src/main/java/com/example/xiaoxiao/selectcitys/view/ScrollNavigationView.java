package com.example.xiaoxiao.selectcitys.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaoxiao.selectcitys.R;

import java.util.List;

/**
 * file name文件名：ScrollNavigationView
 * Description 描述：可以滚动的导航条
 * Created time 创建时间：2017/12/7 13:56
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class ScrollNavigationView extends HorizontalScrollView{
    private Context context;
    private int width;// 最大填充宽度
    private int tab_width; // 项宽
    private int tab_count = 4; // 每屏幕展示项的个数
    private int sum = 0; // 子项的总个数
    private ViewPager viewPager;// 配合使用的ViewPager
    private int height = 50; // 导航高度
    private int crruent = 0; // 当前选中项

    private int select_bg_color; // 选中背景色
    private int defult_bg_color; // 未选中背景色
    private int text_size = 15; // 字体大小
    private int defult_text_color; // 默认字体颜色
    private int select_text_color; // 选中字体颜色


    private boolean has_split = false;//是否显示分割线
    private int split_width = 1; // 分割线宽度
    private LinearLayout ll; //HorizontalScrollView 的直接子布局
    private int barPaddingLeft = 0;

    private boolean isFirst = true;

    List<String> data;

    private OnNavigationItemListener onNavigationItemFocusListener;


    //导航项点击回调
    public interface OnNavigationItemListener {
        public void onNavigationMove(int x);

        public void setmyTranslationX(float x);

        // ViewPager变更时的回调
        public void onFocusChange(int position);
    }

    public ScrollNavigationView(Context context) {
        super(context);
        initView(context);
    }

    public ScrollNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollNavigationView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public int getTab_count() {
        return tab_count;
    }

    public void setTab_count(int tab_count) {
        this.tab_count = tab_count;
    }

    public int getSelectBg() {
        return select_bg_color;
    }

    public void setSelectBg(int select_bg_color) {
        this.select_bg_color = select_bg_color;
    }

    public int getDefultBg() {
        return defult_bg_color;
    }

    public void setDefultBg(int defult_bg_color) {
        this.defult_bg_color = defult_bg_color;
    }

    public boolean isHas_split() {
        return has_split;
    }

    public void setHas_split(boolean has_split) {
        this.has_split = has_split;
    }

    public int getSplit_width() {
        return split_width;
    }

    public void setSplit_width(int split_width) {
        this.split_width = split_width;
    }

    public int getScrollViewHeight() {
        return height;
    }

    public void setScrollViewHeight(int height) {
        this.height = height;
    }

    public float getText_size() {
        return text_size;
    }

    public void setText_size(int text_size) {
        this.text_size = text_size;
    }

    public int getBarPaddingLeft() {
        return barPaddingLeft;
    }

    public void setBarPaddingLeft(int barPaddingLeft) {
        this.barPaddingLeft = barPaddingLeft;
    }

    public int getSelect_text_color() {
        return select_text_color;
    }

    public void setSelect_text_color(int select_text_color) {
        this.select_text_color = select_text_color;
    }

    public int getDefult_text_color() {
        return defult_text_color;
    }

    public void setDefult_text_color(int defult_text_color) {
        this.defult_text_color = defult_text_color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        if (width > 0 && isFirst) {
            initItems(data);
            isFirst = false;
        }
    }

    private void initView(Context context) {
        setHorizontalScrollBarEnabled(false);
        this.context = context;
        height = (int) context.getResources().getDimension(R.dimen.second_navigation_height);//导航高度
        select_bg_color = context.getResources().getColor(R.color.colorBlack);
        defult_bg_color = context.getResources().getColor(R.color.colorBlack);

        select_text_color = context.getResources().getColor(R.color.colorYellow);//选中时游标对应字体得颜色
        defult_text_color = context.getResources().getColor(R.color.colorWhite);//没有选中时游标对应得字体颜色

        // 插入一条线性布局的子布局
        ll = new LinearLayout(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(ll, params);
    }

    // 设置切换控件
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(new MyOnPageChangeListener()); // 页面变化时的监听器
    }

    /**
     * 设置要显示的tab
     *
     * @param position tab所在的位置
     */
    public void setViewPageShowIndex(int position) {
        setSelector(position);
    }

    // 设置点击事件回调方法
    public void setOnNavigationItemClickListener(OnNavigationItemListener onNavigationItemFocusListener) {
        this.onNavigationItemFocusListener = onNavigationItemFocusListener;
    }

    // 批量增加tab页
    public void initItems(List<String> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        this.data = data;
        ll.removeAllViews();
        sum = 0;

        width = (width - barPaddingLeft);
        tab_width = width / tab_count;

        for (int i = 0; i < data.size(); i++) {
            LinearLayout ll_tab = new LinearLayout(context);
            LinearLayout.LayoutParams lp_tab = new LinearLayout.LayoutParams(tab_width, height);
            ll_tab.setOrientation(LinearLayout.HORIZONTAL);
            ll_tab.setLayoutParams(lp_tab);

            if (has_split) {
                View view = new TextView(context);
                LinearLayout.LayoutParams lp_view = new LinearLayout.LayoutParams(split_width, LinearLayout.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp_view);
                view.setBackgroundResource(R.color.colorYellow);//背景色
                ll_tab.addView(view);
            }

            TextView tv = new TextView(context);
            LinearLayout.LayoutParams lp_tv = new LinearLayout.LayoutParams(0, height);
            lp_tv.weight = 1;
            tv.setTag("text");
            tv.setLayoutParams(lp_tv);
            tv.setGravity(Gravity.CENTER);//水平居中
            tv.setText(data.get(i));
            tv.setSingleLine(true);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);
            tv.setTextColor(defult_text_color);
            ll_tab.addView(tv);

            ll_tab.setOnClickListener(tabClickListener);// 设置点击回调
            ll_tab.setId(i);
            ll_tab.setBackgroundColor(defult_bg_color);
            ll.addView(ll_tab);
            sum++;
        }

        // 设置第一个背景色为选中色
        if (ll.getChildCount() > 0) {
            LinearLayout ll_tab = (LinearLayout) ll.getChildAt(0);
            ll_tab.setBackgroundColor(select_bg_color);
            TextView textView = (TextView) ll_tab.findViewWithTag("text");
            textView.setTextColor(select_text_color);
            if (has_split) {
                ll_tab.removeViewAt(0);
            }
        }
    }

    private OnClickListener tabClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setSelector(v.getId());
        }
    };

    /**
     * 设置点击后移动的效果
     * 1判断点击项是否在最左边，是则向右移动为第二项;2否则判断点击项是否在最右边，是则向左移动为倒数第二项;
     * 以上两个条件都不成立则不移动;
     * <p/>
     * 点击后设置字体颜色用来区分选中项
     *
     * @param id
     * @Description
     * @author zyb
     * @date 2015-9-21 下午10:08:07
     */
    public void setSelector(int id) {
        for (int i = 0; i < sum; i++) {
            LinearLayout ll_tab = (LinearLayout) ll.getChildAt(i);
            int[] positions = new int[2];
            ll_tab.getLocationInWindow(positions);

            if (id == i) {
                crruent = i;
                ll_tab.setBackgroundColor(select_bg_color);
                TextView textView = (TextView) ll_tab.findViewWithTag("text");
                textView.setTextColor(select_text_color);
                viewPager.setCurrentItem(i);
                //条件1成立
                /*if (positions[0]<tab_width*2) {
                    smoothScrollTo(tab_width* (i+1-2), 0);
				}else if (positions[0]>(screen_width-tab_width*2)) {
					smoothScrollTo((i - tab_count +2)*tab_width, 0);
				}*/
                /**
                 * 如果当前的tab没有完全在屏幕上那么通过移动将当前的tab显示出来
                 * */
                if (positions[0] < tab_width) {
                    smoothScrollTo(tab_width * i, 0);
                } else if (positions[0] > (width - tab_width)) {
                    smoothScrollTo((i - tab_count + 2) * tab_width, 0);
                }
            } else {
                ll_tab.setBackgroundColor(defult_bg_color);
                TextView textView = (TextView) ll_tab.findViewWithTag("text");
                textView.setTextColor(defult_text_color);
            }
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            // 1. 起始位置
            int startX = position * tab_width;
            // 2. 偏移位置
            float offsetX = (float) (positionOffset * tab_width);
            // 3. 指示器的位置
            float tranX = startX + offsetX;
            if (onNavigationItemFocusListener != null) {
                onNavigationItemFocusListener.setmyTranslationX(tranX);
            }
        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageSelected(int position) {
            setPosition(position);
            crruent = position;
        }
    }

    //移动导航条位置
    public void setPosition(final int position) {
        LinearLayout ll_tab_temp;
        for (int i = 0; i < sum; i++) {
            ll_tab_temp = (LinearLayout) ll.getChildAt(i);
            if (position == i) {
                ll_tab_temp.setBackgroundColor(select_bg_color);
                TextView textView = (TextView) ll_tab_temp.findViewWithTag("text");
                textView.setTextColor(select_text_color);
            } else {
                ll_tab_temp.setBackgroundColor(defult_bg_color);
                TextView textView = (TextView) ll_tab_temp.findViewWithTag("text");
                textView.setTextColor(defult_text_color);
            }
        }
        final LinearLayout ll_tab = (LinearLayout) ll.getChildAt(position);

        /**
         * 这里监视OnPreDrawListener，tab绘制成功后在进行操作，因为如果我直接通过代码进行调用
         * setPosition该方法的时候，tab没有绘制，所以没有获取都tab的大小，因此采用下面的方法进行
         * 获取到tab的大小以后再进行其他的操作
         * */
        final ViewTreeObserver vto = ll_tab.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ll_tab.getViewTreeObserver().removeOnPreDrawListener(this);
                if (onNavigationItemFocusListener != null) {
                    final int[] positions = new int[2];
                    ll_tab.getLocationInWindow(positions);

                    //获取当前选中的tab的位置
                    /**
                     * 根据当前tab的位置进行判断是否要进行移动，如果x坐标在x轴的左侧也就是超出屏幕了
                     * 那么就要移动，将当前的tab显示出来，反之超过了x轴的右侧也应该进行移动位置让tab显示出来
                     * */
                    if (positions[0] < 0) {
                        smoothScrollTo(tab_width * position - getBarPaddingLeft(), 0);
                    } else if (positions[0] > width + getBarPaddingLeft() - tab_width + tab_count) {
                        smoothScrollTo((position + 1 - tab_count) * tab_width, 0);
                    }
                    onNavigationItemFocusListener.onNavigationMove(crruent * tab_width - getScrollX());
                    onNavigationItemFocusListener.onFocusChange(position);
                }
                return true;
            }
        });

    }

    @SuppressLint("NewApi")
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
                                  boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (onNavigationItemFocusListener != null) {
            onNavigationItemFocusListener.onNavigationMove(crruent * tab_width - scrollX);
        }
    }
}
