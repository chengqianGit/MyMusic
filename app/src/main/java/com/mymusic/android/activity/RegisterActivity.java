package com.mymusic.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.mymusic.android.MainActivity;
import com.mymusic.android.R;
import com.mymusic.android.api.Api;
import com.mymusic.android.domain.Session;
import com.mymusic.android.domain.User;
import com.mymusic.android.domain.response.DetailResponse;
import com.mymusic.android.event.LoginSuccessEvent;
import com.mymusic.android.reactivex.HttpListener;
import com.mymusic.android.util.StringUtil;
import com.mymusic.android.util.ToastUtil;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseTitleActivity implements View.OnClickListener {

    EditText et_nickname;
    EditText et_phone;
    EditText et_password;
    Button bt_register;

    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    protected void initViews() {
        super.initViews();
        enableBackMenu();
        et_nickname=findViewById(R.id.et_nickname);
        et_phone=findViewById(R.id.et_phone);
        et_password=findViewById(R.id.et_password);
        bt_register=findViewById(R.id.bt_register);

    }
    @Override
    protected void initListener() {
        super.initListener();
        bt_register.setOnClickListener(this);
    }

    public void register(){
        //startActivityAfterFinishThis(MainActivity.class);
        String nickname = et_nickname.getText().toString();
        if (StringUtils.isBlank(nickname)  ) {
            ToastUtil.showSortToast(getActivity(), R.string.enter_nickname);
            return;
        }

        if (nickname.contains(" ")) {
            //更复杂的，建议用正则表达式
            ToastUtil.showSortToast(getActivity(), R.string.nickname_space);
            return;
        }

        phone = et_phone.getText().toString();
        if (StringUtils.isBlank(phone)) {
            ToastUtil.showSortToast(getActivity(), R.string.hint_phone);
            return;
        }

        if (!StringUtil.isPhone(phone)) {
            ToastUtil.showSortToast(getActivity(), R.string.hint_error_phone);
            return;
        }

        password = et_password.getText().toString();
        if (StringUtils.isBlank(password)) {
            ToastUtil.showSortToast(getActivity(), R.string.hint_password);
            return;
        }

        if (!StringUtil.isPassword(password)) {
            ToastUtil.showSortToast(getActivity(), R.string.hint_error_password_format);
            return;
        }

        User user = new User();
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPassword(password);
        user.setType(User.TYPE_PHONE);


        Api.getInstance().register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpListener<DetailResponse<Session>>(getActivity()) {
                    @Override
                    public void onSucceeded(DetailResponse<Session> data) {
                        super.onSucceeded(data);
                        next(data.getData());
                    }
                });
    }

    //还没有进入到这里
    private void next(Session data) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setType(User.TYPE_PHONE);

        Api.getInstance().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpListener<DetailResponse<Session>>(getActivity()) {
                    @Override
                    public void onSucceeded(DetailResponse<Session> data) {
                        super.onSucceeded(data);
                        sp.setToken(data.getData().getToken());
                        sp.setUserId(data.getData().getId());
                        sp.setIMToken(data.getData().getIm_token());
                        startActivityAfterFinishThis(MainActivity.class);

                        //发布登录成功信息，登录界面好自动关闭
                        EventBus.getDefault().post(new LoginSuccessEvent());
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                register();
                break;
        }
    }
}
