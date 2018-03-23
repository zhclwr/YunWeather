package com.victor.yunweather.model.bean

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Generated


@Entity
class City {

    @Id
    var id: Long? = null

    var cityCode: String? = null
    var cityName: String? = null
    var province: String? = null
    var city: String? = null

    @Generated(hash = 1510656651)
    constructor(id: Long?, cityCode: String, cityName: String, province: String,
                city: String) {
        this.id = id
        this.cityCode = cityCode
        this.cityName = cityName
        this.province = province
        this.city = city
    }

    @Generated(hash = 750791287)
    constructor() {
    }
}
