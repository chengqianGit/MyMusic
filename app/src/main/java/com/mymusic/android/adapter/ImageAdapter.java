package com.mymusic.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.mymusic.android.R;
import com.mymusic.android.domain.Image;
import com.mymusic.android.util.ImageUtil;


/**
 * 动态列表adapter
 * Created by smile on 2018/5/26.
 */

public class ImageAdapter extends BaseQuickRecyclerViewAdapter<Image> {

    public ImageAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void bindData(ViewHolder holder, int position, final Image data) {
        ImageView iv_icon =holder.getView(R.id.iv_icon);
        ImageUtil.show((Activity) context,iv_icon,data.getUri());
    }

}
