package com.mymusic.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mymusic.android.R;
import com.mymusic.android.activity.VideoDetailActivity;
import com.mymusic.android.adapter.BaseRecyclerViewAdapter;
import com.mymusic.android.adapter.VideoAdapter;
import com.mymusic.android.api.Api;
import com.mymusic.android.domain.Video;
import com.mymusic.android.domain.response.ListResponse;
import com.mymusic.android.reactivex.HttpListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 我的视频
 * Created by smile on 02/03/2018.
 */

public class VideoFragment extends BaseCommonFragment {

    RecyclerView rv;
    private VideoAdapter adapter;
    public static VideoFragment newInstance() {

        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        rv = findViewById(R.id.rv);

        adapter = new VideoAdapter(getActivity(),R.layout.item_video);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
                Video data = adapter.getData(position);
                startActivityExtraId(VideoDetailActivity.class,data.getId());
            }
        });

        rv.setAdapter(adapter);

        fetchData();

    }

    private void fetchData() {
        Api.getInstance().videos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpListener<ListResponse<Video>>(getMainActivity()) {
                    @Override
                    public void onSucceeded(ListResponse<Video> data) {
                        super.onSucceeded(data);
                        next(data.getData());
                    }
                });
    }

    public void next(List<Video> videos) {
        adapter.setData(videos);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video,null);
    }

}
