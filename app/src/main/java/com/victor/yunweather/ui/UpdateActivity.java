package com.victor.yunweather.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.victor.yunweather.R;
import com.victor.yunweather.app.App;
import com.victor.yunweather.db.CityDao;
import com.victor.yunweather.db.DaoSession;
import com.victor.yunweather.model.bean.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.tv_load)
    TextView mTvLoad;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.bt_confirm)
    Button mBtConfirm;
    SharedPreferences sp ;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        ButterKnife.bind(this);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getBoolean("data",false)){
            startMain();
        } else {
            handler = new Handler(){
                int num = 0;
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        //增加进度条
                        case 0:
                            num++;
                            mProgressBar.setProgress(num);
                            break;
                        //进度条加载完
                        case 1:
                            mTvLoad.setText("已升级完数据库");
                            mBtConfirm.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            };
            save();
        }
    }
    /**
     * 进入主页面
     * */
    private void startMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void save() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                App app = (App)getApplication();
                DaoSession session = app.getDaoSession();
                CityDao cityDao = session.getCityDao();
                List<City> cities = new ArrayList<>();
                City city;
                InputStream in = null;
                BufferedReader reader = null;
                try{
                    in  = getAssets().open("china-city-list.txt");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    int num = 0;
                    while ((line = reader.readLine())!=null){
                        String[] strs = line.split("\t");
                        city = new City();
                        city.setCityCode(strs[0]);
                        city.setCityName(strs[2]);
                        city.setProvince(strs[7]);
                        city.setCity(strs[9]);
                        cities.add(city);
                        Message msg0 = new Message();
                        msg0.what = 0;
                        handler.sendMessage(msg0);
                    }
                    cityDao.insertInTx(cities);
                    Message msg1 = new Message();
                    msg1.what = 1;
                    handler.sendMessage(msg1);
                }catch (IOException e){
                    e.printStackTrace();
                } finally {
                    try{
                        if (reader != null){
                            reader.close();
                        }
                        if (in != null){
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).run();

    }


    @butterknife.OnClick(R.id.bt_confirm)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_confirm:
                break;
        }
    }
}
