package com.guoyizeng.projecttemplate.ui.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.guoyizeng.mvpbase.ui.activity.BaseActivity;
import com.guoyizeng.projecttemplate.R;
import com.guoyizeng.projecttemplate.contract.MainContract;
import com.guoyizeng.projecttemplate.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.bnv_tab_group)
    BottomNavigationView bnvTabGroup;

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {
        bnvTabGroup.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return true;
                    }
                });
    }

}
