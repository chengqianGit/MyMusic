package com.mymusic.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusic.android.R;
import com.mymusic.android.util.Consts;

import org.apache.commons.lang3.StringUtils;


/**
 * 我的视频
 * Created by smile on 02/03/2018.
 */

public class FeedFragment extends BaseCommonFragment {

    RecyclerView rv;
//    private VideoAdapter adapter;
//    private LRecyclerViewAdapter adapterWrapper;

    public static FeedFragment newInstance(String userId) {

        Bundle args = new Bundle();
        if (StringUtils.isNotBlank(userId)) {
            args.putString(Consts.ID,userId);
        }

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static FeedFragment newInstance() {

        Bundle args = new Bundle();
        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    protected void initViews() {
//        super.initViews();
//        rv=findViewById(R.id.rv);
//        rv.setHasFixedSize(true);
//
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        rv.setLayoutManager(layoutManager);
//    }
//
//    @Override
//    protected void initDatas() {
//        super.initDatas();
//        rv = findViewById(R.id.rv);
//
//        adapter = new VideoAdapter(getActivity(),R.layout.item_video);
//        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
//                Video data = adapter.getData(position);
//                startActivityExtraId(VideoDetailActivity.class,data.getId());
//            }
//        });
//
//        adapterWrapper = new LRecyclerViewAdapter(adapter);
//
//        //adapterWrapper.addHeaderView(createHeaderView());
//        rv.setAdapter(adapterWrapper);
//
//        fetchData();
//
//    }
//
//    private void fetchData() {
//        Api.getInstance().videos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpListener<ListResponse<Video>>(getMainActivity()) {
//                    @Override
//                    public void onSucceeded(ListResponse<Video> data) {
//                        super.onSucceeded(data);
//                        next(data.getData());
//                    }
//                });
//    }
//
//    public void next(List<Video> videos) {
//        adapter.setData(videos);
//    }

    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed,null);
    }

}
