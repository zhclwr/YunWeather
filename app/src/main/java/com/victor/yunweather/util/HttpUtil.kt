package com.victor.yunweather.util

import com.google.gson.Gson
import com.victor.yunweather.BuildConfig
import com.victor.yunweather.model.http.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by victor on 2018/3/20.
 */

object HttpUtil {
    val api: Api
        get() {
            val interceptor = HttpLoggingInterceptor()
            val level: HttpLoggingInterceptor.Level
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            } else {
                level = HttpLoggingInterceptor.Level.NONE
            }
            interceptor.level = level
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                    .baseUrl("https://free-api.heweather.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
        }
}
