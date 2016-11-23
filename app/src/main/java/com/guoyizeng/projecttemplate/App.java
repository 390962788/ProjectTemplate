package com.guoyizeng.projecttemplate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.guoyizeng.mvpbase.BaseApplication;

/**
 * Created by Administrator on 2016/10/26.
 */

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
