package com.lgd.commoncore.app;

/**
 * 是否登陆回调接口
 */

public interface IUserChecker {

    void onSignIn();

    void onNotSignIn();
}
