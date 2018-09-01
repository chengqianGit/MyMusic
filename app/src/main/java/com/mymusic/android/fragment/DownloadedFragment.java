package com.mymusic.android.fragment;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.mymusic.android.R;
import com.mymusic.android.activity.MusicPlayerActivity;
import com.mymusic.android.adapter.BaseRecyclerViewAdapter;
import com.mymusic.android.adapter.DownloadedAdapter;
import com.mymusic.android.domain.Song;
import com.mymusic.android.event.DownloadStatusChanged;
import com.mymusic.android.manager.PlayListManager;
import com.mymusic.android.service.MusicPlayerService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.woblog.android.downloader.DownloadService;
import cn.woblog.android.downloader.callback.DownloadManager;
import cn.woblog.android.downloader.domain.DownloadInfo;


/**
 * 下载完成界面
 * Created by smile on 02/03/2018.
 */

public class DownloadedFragment extends BaseCommonFragment implements View.OnClickListener {

    RecyclerView rv;
    private DownloadManager downloadManager;
    private DownloadedAdapter adapter;
    private PlayListManager playListManager;
    private LinearLayout ll_play_all_container;

    public static DownloadedFragment newInstance() {

        Bundle args = new Bundle();
        DownloadedFragment fragment = new DownloadedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        ll_play_all_container = findViewById(R.id.ll_play_all_container);


        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL);
        rv.addItemDecoration(decoration);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        EventBus.getDefault().register(this);

        downloadManager = DownloadService.getDownloadManager(getActivity().getApplicationContext());
        playListManager = MusicPlayerService.getPlayListManager(getActivity().getApplicationContext());

        adapter = new DownloadedAdapter(getMainActivity(),orm,downloadManager,getChildFragmentManager(),playListManager);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
                play(position);
            }
        });

        rv.setAdapter(adapter);

        fetchData();
    }

    private void play(int position) {
        List<DownloadInfo> downloadInfos = adapter.getDatas();

        ArrayList<Song> songs = new ArrayList<>();
        //根据下载的信息查询出对应的歌曲，然后播放
        for (DownloadInfo downloadInfo : downloadInfos) {
            songs.add(orm.findSongById(downloadInfo.getId()));
        }

        Song data = songs.get(position);
        playListManager.setPlayList(songs);
        playListManager.play(data);
        adapter.notifyDataSetChanged();
        startActivity(MusicPlayerActivity.class);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_play_all_container.setOnClickListener(this);
    }


    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloaded,null);
    }

    private void fetchData() {
        adapter.setData(downloadManager.findAllDownloaded());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downloadStatusChanged(DownloadStatusChanged event) {
        fetchData();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_play_all_container:
                play(0);
                break;
        }
    }
}
