package com.guoyizeng.mvpbase.contract;

/**
 * 契约层
 * 目的是更加清晰的查看需要实现的功能
 */
public interface BaseContract {

    //提供UI 层的契约接口
    interface View {

    }

    //提供Presenter层的契约接口
    interface UserActionsListener {

    }
}
