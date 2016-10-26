package com.guoyizeng.umeng.ui.fragment;

import com.guoyizeng.mvpbase.ui.fragment.BaseFragment;
import com.umeng.analytics.MobclickAgent;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Administrator on 2016/10/26.
 */

public abstract class BaseUmentFragment<P extends Presenter> extends BaseFragment<P> {

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageStart(getActivity().getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageEnd(getActivity().getClass().getName());
    }
}
