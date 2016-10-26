package com.guoyizeng.projecttemplate.contract;


import com.guoyizeng.mvpbase.contract.BaseActivityContract;

/**
 * Created by Administrator on 2016/5/5.
 */
public interface MainContract  {

    //提供UI 层的契约接口
    interface View extends BaseActivityContract.View{



    }

    //提供Presenter层的契约接口
    interface UserActionsListener extends BaseActivityContract.UserActionsListener{

    }
}
