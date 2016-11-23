package com.guoyizeng.projecttemplate.ui.activity;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.guoyizeng.mvpbase.ui.activity.BaseActivity;
import com.guoyizeng.projecttemplate.R;
import com.guoyizeng.projecttemplate.contract.MainContract;
import com.guoyizeng.projecttemplate.presenter.MainPresenter;
import com.wsloan.alipay.PayDemoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.bnv_tab_group)
    BottomNavigationView bnvTabGroup;
    @BindView(R.id.my_image_view)
    SimpleDraweeView myImageView;


    @Override
    protected int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {
        SimpleDraweeView myImageView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        bnvTabGroup = (BottomNavigationView) findViewById(R.id.bnv_tab_group);
        bnvTabGroup.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = new Intent(MainActivity.this, PayDemoActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
        Uri uri = Uri.parse("res:///" + R.mipmap.ic_launcher);
        //Uri uri = Uri.parse("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");


        GenericDraweeHierarchy hierarchy = myImageView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER);

        myImageView.setImageURI(uri);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
