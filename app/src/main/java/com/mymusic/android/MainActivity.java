package com.mymusic.android;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusic.android.activity.BaseTitleActivity;
import com.mymusic.android.activity.LoginActivity;
import com.mymusic.android.activity.UserDetailActivity;
import com.mymusic.android.api.Api;
import com.mymusic.android.domain.User;
import com.mymusic.android.domain.response.DetailResponse;
import com.mymusic.android.reactivex.HttpListener;
import com.mymusic.android.util.UserUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseTitleActivity implements View.OnClickListener {

    private DrawerLayout drawer;

    private static final String TAG = "TAG";
    ImageView iv_avatar;

    TextView tv_nickname;

    TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        super.initViews();
        drawer = findViewById(R.id.drawer_layout);
        iv_avatar = findViewById(R.id.iv_avatar);
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_description = findViewById(R.id.tv_description);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        //显示用户信息
        showUserInfo();
    }

    private void showUserInfo() {
        //用户信息这部分，进来是看不到的，所以可以延后初始化
        if (sp.isLogin()) {
            //调用用户信息接口
            //将用户信息单独放一个工具类中为了以后能重用，其他地方同样需要显示用户信息
            Api.getInstance().userDetail(sp.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpListener<DetailResponse<User>>(getActivity()) {
                        @Override
                        public void onSucceeded(DetailResponse<User> data) {
                            super.onSucceeded(data);
                            showData(data.getData());
                        }
                    });

        } else {
            UserUtil.showNotLoginUser(getActivity(), iv_avatar, tv_nickname, tv_description);
        }
    }

    private void showData(User data) {
        //将显示用户信息放到单独的类中，是为了重用，因为在用户详情界面会用到
        UserUtil.showUser(getActivity(), data, iv_avatar, tv_nickname, tv_description);
    }

    //当点击用户头像时，跳转到用户详情界面
    public void avatarClick() {
        if (sp.isLogin()) {
            startActivityExtraId(UserDetailActivity.class, sp.getUserId());
        } else {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                avatarClick();
                closeDrawer();
                break;
            default:
                break;
        }
    }

    private void closeDrawer() {
        drawer.closeDrawer(Gravity.START);
    }
}
