package com.victor.yunweather.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.victor.yunweather.R
import com.victor.yunweather.app.App
import com.victor.yunweather.base.BaseActivity
import com.victor.yunweather.db.CityDao
import com.victor.yunweather.model.bean.City
import kotlinx.android.synthetic.main.activity_add_city.*
import org.jetbrains.anko.defaultSharedPreferences


class AddCityActivity : BaseActivity() {

    lateinit var cities: ArrayList<City>
    lateinit var adapter:Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        initListener()
        initAdapter()
    }


    private fun initAdapter() {
        cities = ArrayList()
        adapter = Adapter(cities)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        adapter.setOnItemChildClickListener { _, _, position ->
           defaultSharedPreferences.edit().putString("defCity",cities[position].cityCode).apply()
        }
    }

    private fun initListener() {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(et_search.text.toString())
            }

        })
    }

    private fun search(s: String) {
        if (s.isEmpty()){
            cities.clear()
        } else {
            val cityDao = (application as App).daoSession.cityDao
            val temp = cityDao.queryBuilder().where(CityDao.Properties.CityName.like("%"+s+"%")).list()
            cities.addAll(temp)
        }
        Log.i("wasd",cities.size.toString()+"------------------>"+adapter)
        adapter.notifyDataSetChanged()
    }


    inner class Adapter(cities: ArrayList<City>) : BaseQuickAdapter<City, BaseViewHolder>(R.layout.item,cities) {
        override fun convert(helper: BaseViewHolder?, item: City?) {
            helper?.setText(R.id.tv1_item, item?.cityName)
                    ?.setText(R.id.tv2_item, item?.province)
                    ?.setText(R.id.tv3_item, item?.city)
        }
    }

}
