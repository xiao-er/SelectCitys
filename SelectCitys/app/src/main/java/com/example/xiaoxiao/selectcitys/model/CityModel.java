package com.example.xiaoxiao.selectcitys.model;

import java.io.Serializable;

/**
 * file name文件名：CityModel
 * Description 描述：城市的详细数据 这个类就是后台传过来的格式
 * Created time 创建时间：2017/12/7 16:43
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class CityModel implements Serializable {
    public String cityid;
    public String cityno;
    public String initials;
    public String citycode;
    public String cityshortcode;
    public String cityname;
    public String status;
    public String country;
    public String countrycode;
    public boolean domestic;// true 是国内，false 国际
    public String enname;//国际城市 英文名

    @Override
    public String toString() {
        return "CityModel{" +
                "cityid='" + cityid + '\'' +
                ", cityno='" + cityno + '\'' +
                ", initials='" + initials + '\'' +
                ", citycode='" + citycode + '\'' +
                ", cityshortcode='" + cityshortcode + '\'' +
                ", cityname='" + cityname + '\'' +
                ", status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", domestic=" + domestic +
                ", enname='" + enname + '\'' +
                '}';
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityshortcode() {
        return cityshortcode;
    }

    public void setCityshortcode(String cityshortcode) {
        this.cityshortcode = cityshortcode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public boolean getDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }
}
