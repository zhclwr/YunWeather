package com.victor.yunweather.ui

import android.os.Bundle
import android.util.Log
import com.victor.yunweather.R
import com.victor.yunweather.app.App
import com.victor.yunweather.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cityDao = (application as App).daoSession.cityDao
        val list = cityDao.loadAll()
        Log.i("数据库中数据数量--------->",list.size.toString())
    }
}
