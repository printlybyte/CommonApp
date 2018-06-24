package com.lgd.commonec.ec.sign;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.AccountManager;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.delegates.LatteDelegate;
import com.lgd.commoncore.net.RestClient;
import com.lgd.commoncore.net.callback.ISuccess;
import com.lgd.commoncore.util.log.LatteLogger;
import com.lgd.commoncore.util.storage.LattePreference;
import com.lgd.commoncore.wechat.LatteWeChat;
import com.lgd.commoncore.wechat.callbacks.IWeChatSignInCallback;
import com.lgd.commonec.ec.R;
import com.lgd.commonec.ec.R2;
import com.lgd.common_ui.recycler.MultipleFields;
import com.lgd.common_ui.recycler.MultipleItemEntity;
import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.net.RestClient;
import com.lgd.commoncore.net.callback.ISuccess;
import com.lgd.commonec.ec.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 傅令杰 on 2017/4/22
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //取消全屏幕显示
        _mActivity. getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
           String eamil= mEmail.getText().toString();
          String passworf=  mPassword.getText().toString();
          if (eamil.equals("123@123.com")&&passworf.equals("111111")){
              AccountManager.setSignState(true);
              mISignListener.onSignInSuccess();

          }else {
              Toast.makeText(_mActivity, "密码错误", Toast.LENGTH_SHORT).show();
          }
//            RestClient.builder()
//                    .url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
//                    .params("email", mEmail.getText().toString())
//                    .params("password", mPassword.getText().toString())
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            LatteLogger.json("USER_PROFILE", response);
//                            SignHandler.onSignIn(response, mISignListener);
//                        }
//                    })
//                    .build()
//                    .post();


        }
    }

   //唤起微信登陆
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();
    }

    //注册
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    //检测登入的输入是否有问题
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        //邮箱格式的正则匹配
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
       //设置 paddingTop
//        ViewGroup rootView = (ViewGroup) _mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0,  getStatusBarHeight( ), 0, 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //5.0 以上直接设置状态栏颜色
//            _mActivity.getWindow().setStatusBarColor(R.color.app_main);
//        } else {
//            //根布局添加占位状态栏
//            ViewGroup decorView = (ViewGroup) _mActivity.getWindow().getDecorView();
//            View statusBarView = new View(_mActivity);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    getStatusBarHeight( ));
//            statusBarView.setBackgroundColor(R.color.app_main);
//            decorView.addView(statusBarView, lp);
//        }


        return R.layout.delegate_sign_in2;
    }
    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public boolean onBackPressedSupport() {
        _mActivity
                .finish();
        return super.onBackPressedSupport();
    }
}
