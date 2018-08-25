package com.mymusic.android.activity;

import android.os.Bundle;
import android.view.View;

import com.mymusic.android.AppContext;
import com.mymusic.android.MainActivity;
import com.mymusic.android.R;
import com.mymusic.android.event.LoginSuccessEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class LoginActivity extends BaseCommonActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.bt_login)
    public void bt_login() {
        startActivity(LoginPhoneActivity.class);
    }



    @OnClick(R.id.bt_register)
    public void bt_register() {
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.tv_enter)
    public void tv_enter() {
        startActivity(MainActivity.class);
    }


    @OnClick(R.id.iv_login_qq)
    public void iv_login_qq() {
        startActivity(MainActivity.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccessEvent(LoginSuccessEvent event) {
        //连接融云服务器
        //((AppContext)getApplication()).imConnect();
        finish();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
