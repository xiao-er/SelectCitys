package com.example.xiaoxiao.selectcitys.model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * file name文件名：CityListHeaderBean
 * Description 描述：城市列表 HeaderView Bean
 * Created time 创建时间：2017/12/8 14:52
 * author 作者：潇潇
 * email：15010222898@163.com
 */
public class CityListHeaderBean  extends BaseIndexPinyinBean {
    private List<String> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;
    private String initials;//首字母

    @Override
    public String getInitials() {
        return initials;
    }

    @Override
    public void setInitials(String initials) {
        this.initials = initials;
    }

    public CityListHeaderBean() {
    }

    public CityListHeaderBean(List<String> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public CityListHeaderBean setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public CityListHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }

}
