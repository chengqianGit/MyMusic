package com.mymusic.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mymusic.android.R;
import com.mymusic.android.util.Consts;
import com.mymusic.android.util.ImageUtil;
import com.mymusic.android.util.LogUtil;


/**
 * Created by Cheng on 02/06/2018.
 */

public class GuideFragment extends BaseCommonFragment {

    ImageView iv;

    private Integer imageId;

    public static GuideFragment newInstance(int imageId) {

        Bundle args = new Bundle();
        args.putSerializable(Consts.ID, imageId);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        iv=findViewById(R.id.iv);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        imageId = getArguments().getInt(Consts.ID, -1);

        if (imageId == -1) {
            LogUtil.w("Image id can not be empty!");
            getMainActivity().finish();
            return;
        }


        ImageUtil.showLocalImage(getMainActivity(),iv,imageId);

    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide,null);
    }

}
