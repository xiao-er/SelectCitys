package com.example.xiaoxiao.selectcitys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaoxiao.selectcitys.R;
import com.example.xiaoxiao.selectcitys.adapter.CommonAdapter;
import com.example.xiaoxiao.selectcitys.adapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.xiaoxiao.selectcitys.adapter.ViewHolder;
import com.example.xiaoxiao.selectcitys.model.CityListBean;
import com.example.xiaoxiao.selectcitys.model.CityListHeaderBean;
import com.example.xiaoxiao.selectcitys.tools.DividerItemDecoration;
import com.example.xiaoxiao.selectcitys.utils.SharedPreferencesUtils;
import com.example.xiaoxiao.selectcitys.utils.StringUtils;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * file name文件名：HomeCityFragment
 * Description 描述：
 * Created time 创建时间：2017/12/7 14:31
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class HomeCityFragment extends Fragment {

    @BindView(R.id.rv)
    android.support.v7.widget.RecyclerView rv;
    @BindView(R.id.indexBar)
    com.mcxtzhang.indexlib.IndexBar.widget.IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    Unbinder unbinder;
    private List<CityListBean> homeCityList = new ArrayList<CityListBean>();
    private LinearLayoutManager mManager;
    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<CityListHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<CityListBean> mBodyDatas;
    private SuspensionDecoration mDecoration;
    private CommonAdapter<CityListBean> mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private View view;
    private TextView mTvSideBarHint;        //显示指示器DialogText
    private IndexBar mIndexBar;     //右侧边栏导航区域
    private List<String> lateCitys = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_show_city, null);
        unbinder = ButterKnife.bind(this, view);

        mManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mManager);
        initData();
        initView();
        return view;
    }

    private void initData() {
        homeCityList = (List<CityListBean>) getArguments().getSerializable("homeCityList");
    }


    private void initView() {
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> recentCitys = new ArrayList<>();
        CityListHeaderBean mCityListHeaderBean = new CityListHeaderBean(recentCitys, "最近访问城市", "最近");
        mCityListHeaderBean.setInitials("最近");
        mHeaderDatas.add(mCityListHeaderBean);
        List<String> hotCitys = new ArrayList<>();
        CityListHeaderBean cityListHeaderBean = new CityListHeaderBean(hotCitys, "热门城市", "最热");
        cityListHeaderBean.setInitials("最热");
        mHeaderDatas.add(cityListHeaderBean);
        mSourceDatas.addAll(mHeaderDatas);


        mAdapter = new CommonAdapter<CityListBean>(getActivity(), R.layout.citylist_item_select_city, mBodyDatas) {
            @Override
            public void convert(ViewHolder holder, final CityListBean cityBean) {//主体城市显示的Adapter
                //判断城市简称是否为空
                if (cityBean.getCityShortCode() != null && !cityBean.getCityShortCode().isEmpty()) {
                    holder.setText(R.id.txt_cityOrhterInfo, cityBean.getCityShortCode());//赋值
                } else {
                    holder.setText(R.id.txt_cityOrhterInfo, "  ");//这个必须写  不然会错乱
                }
                holder.setText(R.id.tvCity, cityBean.getCity());//赋值

                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "cityName:" + cityBean.getCity(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        //取出城市名称
                        intent.putExtra("cityName", cityBean.getCity());


                    }
                });
            }
        };

        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {


            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.citylist_item_header:
                        final CityListHeaderBean meituanHeaderBean = (CityListHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(getActivity(), R.layout.citylist_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mContext, "cityName:" + cityName, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                intent.putExtra("cityName", cityName);
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        break;

                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(1, R.layout.citylist_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.citylist_item_header, mHeaderDatas.get(1));


        rv.setAdapter(mHeaderAdapter);
        rv.addItemDecoration(mDecoration = new SuspensionDecoration(getActivity(), mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(getActivity().getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) view.findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) view.findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());


        CityListBean[] cityDatasNew = new CityListBean[homeCityList.size()];
        for (int i = 0; i < homeCityList.size(); i++) {
            CityListBean mCityListBean = new CityListBean();
            mCityListBean.setCity(homeCityList.get(i).getCity());
            mCityListBean.setInitials(homeCityList.get(i).getInitials());
            mCityListBean.setCityShortCode(homeCityList.get(i).getCityShortCode());
            cityDatasNew[i] = mCityListBean;
        }

        initDatas(cityDatasNew);   //将城市显示到页面


    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final CityListBean[] data) {
        //主体数据
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBodyDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    CityListBean cityBean = new CityListBean();
                    cityBean.setCity(data[i].getCity());//设置城市名称
                    cityBean.setInitials(data[i].getInitials());
                    cityBean.setCityShortCode(data[i].getCityShortCode());
                    mBodyDatas.add(cityBean);
                }
                for (int i = 0; i < mBodyDatas.size(); i++) {
                    Log.i("mBodyDatas", "name===" + mBodyDatas.get(i).getCity() + "     initials==" + mBodyDatas.get(i).getInitials());
                }
                mAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();

                mSourceDatas.addAll(mBodyDatas);

                mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 100);

        //延迟两秒加载头部
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {


                //这个是最近访问城市，到时候根据自己需求来弄，我这里是写的死数据
                CityListHeaderBean header2 = mHeaderDatas.get(0);
                List<String> recentCitys = new ArrayList<>();
                recentCitys.add("北京");
                recentCitys.add("广州");
                recentCitys.add("上海");
                recentCitys.add("深圳");
                recentCitys.add("南京");
                recentCitys.add("杭州");
                header2.setCityList(recentCitys);
                //热门城市数据
                CityListHeaderBean header3 = mHeaderDatas.get(1);
                List<String> hotCitys = new ArrayList<>();
                hotCitys.add("南京");
                hotCitys.add("上海");
                hotCitys.add("成都");
                hotCitys.add("深圳");
                hotCitys.add("北京");
                hotCitys.add("厦门");
                header3.setCityList(hotCitys);
                mHeaderAdapter.notifyItemRangeChanged(1, 3);

            }
        }, 100);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
