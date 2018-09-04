package com.mymusic.android.manager;


import com.mymusic.android.domain.User;

/**
 * Created by smile on 2018/6/19.
 */

public interface UserManager {
    void getUser(String userId, OnUserListener onUserListener);

    interface OnUserListener{
        void onUser(User user);
    }
}
