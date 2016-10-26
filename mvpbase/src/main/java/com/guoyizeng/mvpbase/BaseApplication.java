package com.guoyizeng.mvpbase;

import android.app.Application;

import com.guoyizeng.mvpbase.util.CrashHandler;

/**
 * Created by Administrator on 2016/8/2.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
