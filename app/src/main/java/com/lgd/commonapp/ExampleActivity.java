package com.lgd.commonapp;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.lgd.common_ui.launcher.ILauncherListener;
import com.lgd.common_ui.launcher.OnLauncherFinishTag;
import com.lgd.commoncore.activitys.ProxyActivity;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.delegates.LatteDelegate;
import com.lgd.commonec.ec.launcher.LauncherDelegate;
import com.lgd.commonec.ec.launcher.LauncherScrollCostomDelegate;
import com.lgd.commonec.ec.main.EcBottomDelegate;
import com.lgd.commonec.ec.sign.ISignListener;
import com.lgd.commonec.ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;


public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
//
//        getWindow().clearFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
//        StatusBarCompat.translucentStatusBar(this, true);
        //SDK >= 21时, 取消状态栏的阴影 255完全不透明  默认完全透明  沉浸式
        StatusBarCompat.setStatusBarColor(this,DEFAULT_COLOR ,255
        );


    }

    @Override
    protected void onPause() {
        super.onPause();
//        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show(); getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().start(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().start(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
