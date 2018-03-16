package com.victor.yunweather.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.victor.yunweather.R
import kotlinx.android.synthetic.main.activity_load.*
import org.jetbrains.anko.defaultSharedPreferences

class LoadActivity : AppCompatActivity() {

    val sp = defaultSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        if (sp.getBoolean("data",false)){
            startActivity(Intent(this,MainActivity::class.java))
        } else {
            sava()
        }
        progressBar
    }

    private fun sava() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
