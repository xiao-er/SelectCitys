package com.example.xiaoxiao.selectcitys.model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

/**
 * file name文件名：CityListBean
 * Description 描述：
 * Created time 创建时间：2017/12/8 14:14
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class CityListBean extends BaseIndexPinyinBean implements Serializable {
    private String city;//城市名字
    private String cityShortCode;//城市的简称
    private String cityCounty;//城市对应的国家

    public String getCityShortCode() {
        return cityShortCode;
    }

    public void setCityShortCode(String cityShortCode) {
        this.cityShortCode = cityShortCode;
    }

    public String getCityCounty() {
        return cityCounty;
    }

    public void setCityCounty(String cityCounty) {
        this.cityCounty = cityCounty;
    }

    public CityListBean() {
    }

    public CityListBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public CityListBean setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }
}
