package com.mymusic.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.mymusic.android.R;
import com.mymusic.android.domain.User;
import com.mymusic.android.util.ImageUtil;


/**
 * 好友列表adapter
 * Created by smile on 2018/5/26.
 */

public class FriendAdapter extends BaseQuickRecyclerViewAdapter<User> {

    public FriendAdapter(Context context, int layoutId) {
        super(context, layoutId);

    }

    @Override
    protected void bindData(ViewHolder holder, int position, final User data) {
        ImageUtil.showCircle((Activity) context, (ImageView) holder.getView(R.id.iv_avatar),data.getAvatar());
        holder.setText(R.id.tv_nickname,data.getNickname());
        holder.setText(R.id.tv_info,data.getDescription());
    }

}
