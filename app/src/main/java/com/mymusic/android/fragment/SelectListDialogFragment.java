package com.mymusic.android.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mymusic.android.R;
import com.mymusic.android.adapter.BaseRecyclerViewAdapter;
import com.mymusic.android.adapter.ListAdapter;
import java.util.List;


/**
 * 选择歌单
 * Created by smile on 2018/5/31.
 */

public class SelectListDialogFragment extends BottomSheetDialogFragment {
    private RecyclerView rv;
    public ListAdapter adapter;
    private List<com.mymusic.android.domain.List> data;
    private OnSelectListListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv= view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL);
        rv.addItemDecoration(decoration);

        adapter = new ListAdapter(getActivity(), R.layout.item_me_list);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerViewAdapter.ViewHolder holder, int position) {
                dismiss();
                listener.onSelectListClick(data.get(position));
            }
        });

        rv.setAdapter(adapter);

        adapter.setData(data);
        //fetchData();
    }

    //private void fetchData() {
    //    Observable<ListResponse<com.ixuea.courses.mymusic.domain.List>> list = Api.getInstance().listsMyCreate();
    //    list.subscribeOn(Schedulers.io())
    //            .observeOn(AndroidSchedulers.mainThread())
    //            .subscribe(new HttpListener<ListResponse<cn.badu.and.roid.me.app.domain.List>>((BaseActivity) getActivity()) {
    //                @Override
    //                public void onSucceeded(final ListResponse<cn.badu.and.roid.me.app.domain.List> data) {
    //                    super.onSucceeded(data);
    //                    adapter.setData(data.getData());
    //                }
    //            });
    //
    //}



    //public static void show(FragmentManager fragmentManager,  OnSelectListListener listener) {
    //    SelectListDialogFragment fragment = new SelectListDialogFragment();
    //    fragment.setListener(listener);
    //    fragment.show(fragmentManager,"SelectListDialogFragment");
    //}

    public static void show(FragmentManager fragmentManager, List<com.mymusic.android.domain.List> data, OnSelectListListener listener) {
            SelectListDialogFragment fragment = new SelectListDialogFragment();
            fragment.setData(data);
            fragment.setListener(listener);
            fragment.show(fragmentManager,"SelectListDialogFragment");
        }

    private void setData(List<com.mymusic.android.domain.List> data) {
        this.data=data;
    }

    private void setListener(OnSelectListListener listener) {
        this.listener=listener;
    }

    public interface OnSelectListListener{
        void onSelectListClick(com.mymusic.android.domain.List list);
    }
}
