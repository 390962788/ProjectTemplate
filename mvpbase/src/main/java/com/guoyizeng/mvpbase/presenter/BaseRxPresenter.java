package com.guoyizeng.mvpbase.presenter;

import android.os.Bundle;

import nucleus.presenter.RxPresenter;

/**
 * Created by Administrator on 2016/10/26.
 */

public abstract class BaseRxPresenter<View> extends RxPresenter<View> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }
}
