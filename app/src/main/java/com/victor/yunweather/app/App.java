package com.victor.yunweather.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.victor.yunweather.db.DaoMaster;
import com.victor.yunweather.db.DaoSession;


/**
 * Created by victor on 2018/3/16.
 */

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"cities.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
