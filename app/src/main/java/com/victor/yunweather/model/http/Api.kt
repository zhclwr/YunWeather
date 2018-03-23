package com.victor.yunweather.model.http

import com.victor.yunweather.model.bean.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://free-api.heweather.com/v5/weather?city=CN101191303&key=ac4cceaa37be4d7099f04b2b0b456041

interface Api{
    @GET("v5/weather")
    fun getWeather(@Query("city")cityId:String, @Query("key")key:String):Call<Weather>
}