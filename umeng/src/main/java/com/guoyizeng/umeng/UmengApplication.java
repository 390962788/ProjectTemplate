package com.guoyizeng.umeng;

import com.guoyizeng.mvpbase.BaseApplication;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/10/26.
 */

public class UmengApplication extends BaseApplication {

    private static final String APP_KEY = "";
    private static final String CHANNEL_ID = "";
    private static final String SECRET_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();

    }

    private void initUmeng() {
        MobclickAgent.UMAnalyticsConfig  config = new MobclickAgent.UMAnalyticsConfig(getApplicationContext(),APP_KEY,CHANNEL_ID);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setSecret(getApplicationContext(), SECRET_KEY);
    }
}
