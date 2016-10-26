package com.guoyizeng.projecttemplate.ui.activity;


import android.os.Bundle;
import android.os.PersistableBundle;

import com.guoyizeng.mvpbase.ui.activity.BaseActivity;
import com.guoyizeng.projecttemplate.R;
import com.guoyizeng.projecttemplate.contract.MainContract;
import com.guoyizeng.projecttemplate.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onNetworkError() {

    }
}
