package com.guoyizeng.mvpbase.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.guoyizeng.mvpbase.contract.BaseContract;

import butterknife.ButterKnife;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Created by Administrator on 2016/8/2.
 */
public abstract class BaseActivity<PresenterType extends Presenter> extends NucleusAppCompatActivity<PresenterType> implements BaseContract.View{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //屏幕方向
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(setLayoutResId());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract int setLayoutResId();
    protected abstract void initView();
}
