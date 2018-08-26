package com.mymusic.android.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mymusic.android.fragment.AboutUserFragment;
import com.mymusic.android.fragment.FeedFragment;
import com.mymusic.android.fragment.UserDetailMusicFragment;


/**
 * Created by smile on 2018/5/26.
 */

public class UserDetailAdapter extends BaseFragmentPagerAdapter<Integer> {
    private static String[] titleNames = {"音乐", "动态", "关于我"};
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDetailAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return UserDetailMusicFragment.newInstance();
        } else if (position == 1) {
            return FeedFragment.newInstance(userId);
        } else {
            return AboutUserFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleNames[position];
    }
}