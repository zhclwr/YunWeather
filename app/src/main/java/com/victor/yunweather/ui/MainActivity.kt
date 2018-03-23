package com.victor.yunweather.ui

import android.content.Intent
import android.os.Bundle
import com.victor.yunweather.R
import com.victor.yunweather.app.App
import com.victor.yunweather.base.BaseActivity
import com.victor.yunweather.db.CityDao
import kotlinx.android.synthetic.main.title.*
import org.jetbrains.anko.toast

/**
 * https://free-api.heweather.com/v5/weather?city=yourcity&key=yourkey
 *
 * key: ac4cceaa37be4d7099f04b2b0b456041
 *
 * such as : https://free-api.heweather.com/v5/weather?city=CN101191303&key=ac4cceaa37be4d7099f04b2b0b456041
 * */

class MainActivity : BaseActivity() {


    lateinit var app: App
    lateinit var cityDao: CityDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as App
        cityDao = app.daoSession.cityDao
        val t = cityDao.count()
        toast(""+t)
        initListener()

    }

    private fun initListener() {
        image_add.setOnClickListener { startActivity(Intent(this,AddCityActivity::class.java)) }
        nav_button.setOnClickListener {  startActivity(Intent(this,AddCityActivity::class.java)) }
    }
}
