package com.mymusic.android.fragment;


import com.mymusic.android.util.OrmUtil;
import com.mymusic.android.util.SharedPreferencesUtil;


/**
 * Created by Cheng on 02/06/2018.
 */

public abstract class BaseCommonFragment extends BaseFragment {
    protected SharedPreferencesUtil sp;
    protected OrmUtil orm;

    @Override
    protected void initViews() {
        super.initViews();

        sp = SharedPreferencesUtil.getInstance(getActivity().getApplicationContext());
        orm = OrmUtil.getInstance(getActivity().getApplicationContext());
    }


}
