package com.mymusic.android.util;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymusic.android.R;
import com.mymusic.android.domain.User;


/**
 * Created by smile on 05/03/2018.
 */

public class UserUtil {
    public static void showUser(Activity activity, User data, ImageView iv_user_avatar, TextView tv_user_nickname, TextView tv_user_description) {
        if (data == null) {
            //如果数据为空，就显示默认的头像
            ImageUtil.showCircle(activity, iv_user_avatar, R.drawable.default_avatar);
        } else {
            ImageUtil.showCircle(activity, iv_user_avatar, data.getAvatar());
            tv_user_nickname.setText(data.getNickname());
            tv_user_description.setText(data.getDescription());

        }

    }

    //若以游客身份进入
    public static void showNotLoginUser(Activity activity,  ImageView iv_user_avatar, TextView tv_user_nickname, TextView tv_user_description) {
        ImageUtil.showCircle(activity, iv_user_avatar, R.drawable.default_avatar);
        tv_user_nickname.setText("请先登录");
        tv_user_description.setText("");


    }
}
