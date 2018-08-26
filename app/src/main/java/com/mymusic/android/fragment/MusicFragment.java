package com.mymusic.android.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mymusic.android.R;

import java.util.ArrayList;

/**
 * 我的推荐
 * Created by smile on 02/03/2018.
 */

public class MusicFragment extends BaseCommonFragment {

    //子View不能BindView
    //这就是为什么成熟的商业项目尽量少用第三库
    //因为在你没有完全搞懂一个框架时，你永远不知道有多少坑等你

    //@BindView(R.id.tabs)
//    MagicIndicator tabs;
//
//    //@BindView(R.id.vp)
//    ViewPager vp;
//
//    private MusicUIAdapter adapter;

    public static MusicFragment newInstance() {

        Bundle args = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    protected void initViews() {
//        super.initViews();
//        tabs=findViewById(R.id.tabs);
//        vp=findViewById(R.id.vp);
//
//        vp.setOffscreenPageLimit(3);
//
//    }
//
//    @Override
//    protected void initDatas() {
//        super.initDatas();
//        //这里一定要调用childFragmentManager
//        adapter = new MusicUIAdapter(getActivity(), getChildFragmentManager());
//        vp.setAdapter(adapter);
//
//        final ArrayList<Integer> datas = new ArrayList<>();
//        datas.add(0);
//        datas.add(1);
//        datas.add(2);
//        adapter.setDatas(datas);
//
//        //将TabLayout和ViewPager关联起来
//        CommonNavigator commonNavigator = new CommonNavigator(getMainActivity());
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//
//            @Override
//            public int getCount() {
//                return datas.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
//                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.text_white));
//                colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
//                colorTransitionPagerTitleView.setText(adapter.getPageTitle(index));
//                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        vp.setCurrentItem(index);
//                    }
//                });
//                return colorTransitionPagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                indicator.setColors(Color.WHITE);
//                return indicator;
//            }
//        });
//        commonNavigator.setAdjustMode(true);
//        tabs.setNavigator(commonNavigator);
//
//        ViewPagerHelper.bind(tabs, vp);
//    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music,null);
    }

}
