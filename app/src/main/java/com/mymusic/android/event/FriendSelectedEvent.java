package com.mymusic.android.event;


import com.mymusic.android.domain.User;

/**
 * Created by smile on 2018/5/26.
 */

public class FriendSelectedEvent {
    private User user;

    public FriendSelectedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
