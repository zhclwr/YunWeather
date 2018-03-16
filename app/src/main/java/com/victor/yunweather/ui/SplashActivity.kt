package com.victor.yunweather.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.victor.yunweather.R
import com.victor.yunweather.app.App
import com.victor.yunweather.base.BaseActivity
import com.victor.yunweather.model.bean.City
import kotlinx.android.synthetic.main.activity_load.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SplashActivity : BaseActivity() {

     private lateinit var sp:SharedPreferences
//    sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        sp = defaultSharedPreferences
        if (!sp.getBoolean("data", false)) {
            sava()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        bt_confirm.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun sava() {
        doAsync {
            val cityDao = (application as App).daoSession.cityDao
            val assets = assets
            val file = assets.open("china-city-list.txt")
            val reader = file.reader()
            val lines = reader.readLines()
            val cities = ArrayList<City>()
            var num = 0
            Log.i("lines size ------->", lines.size.toString())
            for (i in lines) {
                val strs = i.split("\t")
                val city = City().apply {
                    id = num
                    cityCode = strs[0]
                    cityName = strs[2]
                    province = strs[7]
                    city = strs[9]
                }
//                cityDao.insert(city)
                cities.add(city)
                num++
                if (num in 0..3181 step 31) {
                    uiThread { progressBar.progress++ }
                }
//                Log.i("num\t=",num.toString())
//                Log.i("progress\t= ",progress.toString())
            }
            cityDao.insertInTx(cities)
            sp.edit().putBoolean("data",true).apply()
            uiThread {
                tv_load.text = "数据库升级完成"
                bt_confirm.visibility = View.VISIBLE
            }
            defaultSharedPreferences.edit().putBoolean("data", true).apply()
            val list = cityDao.loadAll()
            Log.i("数据库中数据数量--------->",list.size.toString())

        }

    }
}
