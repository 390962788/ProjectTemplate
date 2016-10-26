package com.guoyizeng.umeng.util;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/10/26.
 */

public class EventUtil {

    public static void setEventLogin(Context context){
        MobclickAgent.onEvent(context, EventType.LOGIN);
    }

    public static void setEventExit(Context context){
        MobclickAgent.onEvent(context, EventType.EXIT);
    }

    public static void setEventGetCash(Context context){
        MobclickAgent.onEvent(context, EventType.GET_CASH);
    }

    public static void setEventTopUp(Context context){
        MobclickAgent.onEvent(context, EventType.TOP_UP);
    }

}
