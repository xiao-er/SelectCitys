package com.example.xiaoxiao.selectcitys.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xiaoxiao.selectcitys.R;
import com.example.xiaoxiao.selectcitys.adapter.MyFragmentPagerAdapter;
import com.example.xiaoxiao.selectcitys.ui.fragment.HomeCityFragment;
import com.example.xiaoxiao.selectcitys.ui.fragment.InternatCityFragment;
import com.example.xiaoxiao.selectcitys.model.CityListBean;
import com.example.xiaoxiao.selectcitys.model.CityModel;
import com.example.xiaoxiao.selectcitys.view.MyViewPager;
import com.example.xiaoxiao.selectcitys.view.ScrollNavigationCursorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.sncv_info)
    ScrollNavigationCursorView sncvInfo;
    @BindView(R.id.vp_info)
    MyViewPager vpInfo;
    private List<String> strings = Arrays.asList("国内", "国际/地区");//游标的数据
    private HomeCityFragment homeCityFragment;//展示国内城市数据的fragment
    private InternatCityFragment internatCityFragment;//展示国际城市数据的fragment
    private List<String> initialsList = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M");
    private List<CityModel> cityModelList = new ArrayList<CityModel>();
    private List<CityListBean> homeCityList = new ArrayList<CityListBean>();//国内城市的数据
    private List<CityListBean> internatCityList = new ArrayList<CityListBean>();//国际城市的数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getDatas();
        initView();
        initData();
    }


    /**
     * 假数据，我这个地方是根据我们后台给我传的模式来制造假数据的
     * 虽然是假数据 我也尽量搞的完美点
     */
    private void getDatas() {
        //我们把每个对应的首字母都加五条把
        for (int i = 0; i < initialsList.size(); i++) {
            if (initialsList.get(i).toString().equalsIgnoreCase("A")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("安顺");
                        cityModel.setCityshortcode("AVA");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("鞍山");
                        cityModel.setCityshortcode("AOG");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 3) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("澳门");
                        cityModel.setCityshortcode("MFM");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    } else if (j == 4) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("阿姆斯特丹");
                        cityModel.setCityshortcode("AMS");
                        cityModel.setCountry("荷兰");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    }
                }
            } else if (initialsList.get(i).toString().equalsIgnoreCase("C")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("长春");
                        cityModel.setCityshortcode("CGQ");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("长沙");
                        cityModel.setCityshortcode("CSX");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    }
                }
            } else if (initialsList.get(i).toString().equalsIgnoreCase("B")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("北京");
                        cityModel.setCityshortcode("BJS");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("保定");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 3) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("巴黎");
                        cityModel.setCityshortcode("PAR");
                        cityModel.setCountry("法国");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    } else if (j == 4) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("巴厘岛");
                        cityModel.setCityshortcode("DPS");
                        cityModel.setCountry("印度尼西亚");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    }
                }

            } else if (initialsList.get(i).toString().equalsIgnoreCase("D")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("大连");
                        cityModel.setCityshortcode("DLG");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("大同");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 3) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("东京");
                        cityModel.setCityshortcode("PAR");
                        cityModel.setCountry("法国");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    } else if (j == 4) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("巴厘岛");
                        cityModel.setCityshortcode("DPS");
                        cityModel.setCountry("印度尼西亚");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    }
                }
            } else if (initialsList.get(i).toString().equalsIgnoreCase("F")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("佛山");
                        cityModel.setCityshortcode("FUO");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("福州");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    }
                }

            } else if (initialsList.get(i).toString().equalsIgnoreCase("G")) {
                for (int j = 0; j < 5; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("广州");
                        cityModel.setCityshortcode("CAN");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 7) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("广元");
                        cityModel.setCityshortcode("GYS");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("贵阳");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 5) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("桂林");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 6) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("广安");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 3) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("哥本哈根");
                        cityModel.setCityshortcode("CPH");
                        cityModel.setCountry("丹麦");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    } else if (j == 4) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("哥本哈");
                        cityModel.setCityshortcode("CPH");
                        cityModel.setCountry("丹麦");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    }
                }

            } else if (initialsList.get(i).toString().equalsIgnoreCase("H")) {
                for (int j = 0; j < 20; j++) {
                    if (j == 1) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("黄冈");
                        cityModel.setCityshortcode("FUO");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 2) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("淮北");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 3) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("海口");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 4) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("怀化");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 5) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("汉中");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 6) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("黑河");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 7) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("衡水");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 8) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("哈尔滨");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 9) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("池州");
                        cityModel.setCityshortcode("FOC");
                        cityModel.setDomestic(true);
                        cityModelList.add(cityModel);
                    } else if (j == 10) {
                        CityModel cityModel = new CityModel();
                        cityModel.setInitials(initialsList.get(i).toString());
                        cityModel.setCityname("赫尔辛基");
                        cityModel.setCityshortcode("HEL");
                        cityModel.setCountry("芬兰");
                        cityModel.setDomestic(false);
                        cityModelList.add(cityModel);
                    }
                }

            }
        }
        Log.i("cityModelList", cityModelList.toString());


        //分别取出国际 和  国内的数据
        for (int i = 0; i < cityModelList.size(); i++) {
            //如果Domestic ==true 代表这国内  如果Domestic ==false 代表国际
            if (cityModelList.get(i).getDomestic()) {
                CityListBean cityListBean = new CityListBean();
                cityListBean.setInitials(cityModelList.get(i).getInitials());
                cityListBean.setCity(cityModelList.get(i).getCityname());
                if (cityModelList.get(i).getCityshortcode() != null && !cityModelList.get(i).getCityshortcode().isEmpty()) {
                    cityListBean.setCityShortCode(cityModelList.get(i).getCityshortcode());
                }
                homeCityList.add(cityListBean);
            } else {
                CityListBean cityListBean = new CityListBean();
                cityListBean.setInitials(cityModelList.get(i).getInitials());
                cityListBean.setCity(cityModelList.get(i).getCityname());
                if (cityModelList.get(i).getCityshortcode() != null && !cityModelList.get(i).getCityshortcode().isEmpty()) {
                    cityListBean.setCityShortCode(cityModelList.get(i).getCityshortcode());
                }
                if (cityModelList.get(i).getCountry() != null && !cityModelList.get(i).getCountry().isEmpty()) {
                    cityListBean.setCityCounty(cityModelList.get(i).getCountry());
                }
                internatCityList.add(cityListBean);
            }

        }

        for (CityListBean cityListBean : homeCityList) {
            Log.i("homeCityList", cityListBean.getCity().toString() +
                    ";bean=" + cityListBean.getInitials().toString());
        }
        for (CityListBean cityListBean : internatCityList) {
            Log.i("internatCityList", cityListBean.getCity().toString() +
                    ";bean=" + cityListBean.getInitials().toString());
        }

    }


    /**
     * 创建时间：2017/12/7 14:28
     * 描述：初始化
     * 参数：
     * 返回值（有/无）：无
     */
    private void initView() {
        sncvInfo.setTab_count(2);//设置每屏幕显示tab的个数
        sncvInfo.setHas_split(false);//是否显示分割线
        sncvInfo.setIs_boarder_bottom_line(true);//是否有顶部边界
        sncvInfo.setViewPager(vpInfo);
        sncvInfo.initItems(strings);//设置tab数据
    }


    /**
     * 创建时间：2017/12/7 16:12
     * 描述：绑定fragment 并起需要的数据传递过去
     * 参数：
     * 返回值（有/无）：
     */

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        homeCityFragment = new HomeCityFragment();
        internatCityFragment = new InternatCityFragment();
        //使用setArguments传递数据，防止横屏数据传递有错
        Bundle cityData = new Bundle();
        cityData.putSerializable("homeCityList", (ArrayList<CityListBean>) homeCityList);//国内城市数据
        cityData.putSerializable("internatCityList", (ArrayList<CityListBean>) internatCityList);//国际城市数据
        homeCityFragment.setArguments(cityData);
        internatCityFragment.setArguments(cityData);
        fragments.add(homeCityFragment);
        fragments.add(internatCityFragment);
        vpInfo.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()
                , fragments));
        vpInfo.setOffscreenPageLimit(2);//往后预加载几个页面
        vpInfo.setCanScroll(false);
        vpInfo.setCurrentItem(0);
    }


}
