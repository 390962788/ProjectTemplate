package com.guoyizeng.umeng;

import com.guoyizeng.mvpbase.BaseApplication;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2016/10/26.
 */

public class UmengApplication extends BaseApplication {
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }

    private static final String APP_KEY = "";
    private static final String CHANNEL_ID = "";
    private static final String SECRET_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();

    }

    private void initUmeng() {
        UMShareAPI.get(this);
        Config.REDIRECT_URL = "您新浪后台的回调地址";
//        new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
//                .withText("hello")
//                .setCallback(umShareListener)
//                .share();

        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(getApplicationContext(), APP_KEY, CHANNEL_ID);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setSecret(getApplicationContext(), SECRET_KEY);
    }
}
