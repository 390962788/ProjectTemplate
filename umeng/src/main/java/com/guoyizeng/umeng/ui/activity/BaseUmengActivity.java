package com.guoyizeng.umeng.ui.activity;

import com.guoyizeng.mvpbase.ui.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import nucleus.presenter.Presenter;

/**
 * Created by Administrator on 2016/10/26.
 */

public abstract class BaseUmengActivity<PresenterType extends Presenter> extends BaseActivity<PresenterType> {



    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (!isHasFragment()){
            MobclickAgent.onPageStart(getClass().getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (!isHasFragment()){
            MobclickAgent.onPageEnd(getClass().getName());
        }
    }

    protected abstract boolean isHasFragment();
}
