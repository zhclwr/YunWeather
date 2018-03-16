package com.victor.yunweather.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by victor on 2018/3/16.
 */

@Entity
public class City {
    @Id
    private int id;

    private String cityCode;
    private String cityName;
    private String province;
    private String city;
    @Generated(hash = 2105453691)
    public City(int id, String cityCode, String cityName, String province,
            String city) {
        this.id = id;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.province = province;
        this.city = city;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }



}
