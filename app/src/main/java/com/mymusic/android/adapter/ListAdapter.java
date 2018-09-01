package com.mymusic.android.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.mymusic.android.R;
import com.mymusic.android.activity.BaseActivity;
import com.mymusic.android.domain.List;
import com.mymusic.android.util.ImageUtil;


/**
 * 歌单列表adapter
 * Created by smile on 2018/5/26.
 */

public class ListAdapter extends BaseQuickRecyclerViewAdapter<List> {

    public ListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void bindData(ViewHolder holder, final int position, List data) {
        ImageUtil.show((BaseActivity)context, (ImageView) holder.getView(R.id.iv_icon),data.getBanner());
        holder.setText(R.id.tv_title,data.getTitle());
        holder.setText(R.id.tv_count,context.getResources().getString(R.string.song_count,data.getSongs_count()));
    }
}
