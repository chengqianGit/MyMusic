package com.mymusic.android.activity;


import android.os.Bundle;

import com.mymusic.android.AppContext;
import com.mymusic.android.R;
import com.mymusic.android.event.LogoutSuccessEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import okhttp3.internal.platform.Platform;

public class SettingsActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    @Override
    protected void initViews() {
        super.initViews();
        enableBackMenu();
    }

    @OnClick(R.id.bt_logout)
    public void bt_logout() {
        sp.logout();
        //AppContext.logout();

        //Tencent tencent = Tencent.createInstance(Consts.QQ_KEY, this.getApplicationContext());
        //tencent.logout(this);

        //清除第三方登陆信息
//        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//        if (qq.isAuthValid()) {
//            qq.removeAccount(true);
//        }
//
        //发布退出登陆的信息，因为首页要根据登陆状态显示
        EventBus.getDefault().post(new LogoutSuccessEvent());

        finish();
    }
}
