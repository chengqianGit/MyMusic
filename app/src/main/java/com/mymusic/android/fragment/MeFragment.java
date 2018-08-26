package com.mymusic.android.fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.android.R;

/**
 * 我的推荐-我的
 * Created by smile on 02/03/2018.
 */

public class MeFragment extends BaseCommonFragment{

//    private ExpandableListView elv;
//    //private MeAdapter adapter;
//    private LinearLayout ll_local_music;
//    private LinearLayout ll_download;
//    private TextView tv_music_count;
//    private TextView tv_download_count;
//    private DownloadManager downloadManager;

    public static MeFragment newInstance() {

        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    protected void initViews() {
//        super.initViews();
//        elv=findViewById(R.id.elv);
//
//        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_me, elv, false);
//        elv.addHeaderView(headerView);
//
//        ll_local_music=findViewById(R.id.ll_local_music);
//        tv_music_count=findViewById(R.id.tv_music_count);
//        tv_download_count=findViewById(R.id.tv_download_count);
//        ll_download=findViewById(R.id.ll_download);
//    }
//
//    @Override
//    protected void initDatas() {
//        super.initDatas();
//        downloadManager = DownloadService.getDownloadManager(getActivity().getApplicationContext());
//
//        tv_music_count.setText(getResources().getString(R.string.music_count1,orm.countOfLocalMusic(sp.getUserId())));
//        tv_download_count.setText(getResources().getString(R.string.music_count1,downloadManager.findAllDownloaded().size()));
//
//        adapter = new MeAdapter(getActivity());
//        adapter.setOnMeListener(this);
//        elv.setAdapter(adapter);
//
//        fetchData();
//    }
//
//
//    private void fetchData() {
//        final ArrayList<MeUI> d = new ArrayList<>();
//
//        Observable<ListResponse<List>> list = Api.getInstance().listsMyCreate();
//        list.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpListener<ListResponse<List>>(getMainActivity()) {
//                    @Override
//                    public void onSucceeded(final ListResponse<cn.badu.and.roid.me.app.domain.List> data) {
//                        super.onSucceeded(data);
//                        d.add(new MeUI("我创建的歌单",data.getData()));
//
//                        Api.getInstance().listsMyCollection().subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new HttpListener<ListResponse<List>>(getMainActivity()) {
//                                    @Override
//                                    public void onSucceeded(final ListResponse<cn.badu.and.roid.me.app.domain.List> data) {
//                                        super.onSucceeded(data);
//                                        d.add(new MeUI("我收藏的歌单",data.getData()));
//                                        adapter.setData(d);
//                                    }
//                                });
//                    }
//                });
//    }
//
//
//    @Override
//    protected void initListener() {
//        super.initListener();
//        ll_local_music.setOnClickListener(this);
//        ll_download.setOnClickListener(this);
//        elv.setOnChildClickListener(this);
//    }
//
//
    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,null);
    }


//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_local_music:
//                startActivity(LocalMusicActivity.class);
//                break;
//            case R.id.ll_download:
//                startActivity(DownloadManagerActivity.class);
//                break;
//        }
//    }
//
//    @Override
//    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//        List data = adapter.getChildData(groupPosition, childPosition);
//        startActivityExtraId(ListDetailActivity.class,data.getId());
//        return true;
//    }
//
//    @Override
//    public void onListGroupSettingsClick() {
//        ListGroupMoreDialogFragment.show(getChildFragmentManager(),this);
//    }
//
//    @Override
//    public void onCreateList() {
//        //创建歌单
//        CreateListDialogFragment.show(getChildFragmentManager(), new CreateListDialogFragment.OnConfirmCreateListListener() {
//            @Override
//            public void onConfirmCreateListClick(String text) {
//                createDialog(text);
//            }
//        });
//
//    }
//
//    private void createDialog(String text) {
//        List list = new List();
//        //这里不要穿用户id，不然这就是一个漏洞，就可以给任何人创建歌单
//        //而是服务端根据token获取用户信息
//        list.setTitle(text);
//
//        Api.getInstance().createList(list)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new HttpListener<DetailResponse<List>>(getMainActivity()) {
//                    @Override
//                    public void onSucceeded(DetailResponse<List> data) {
//                        super.onSucceeded(data);
//                        ToastUtil.showSortToast(getMainActivity(),getString(R.string.list_create_susscess));
//                        fetchData();
//                    }
//                });
//    }
//
//    @Override
//    public void onManagerList() {
//        //TODO 管理歌单
//    }
}
