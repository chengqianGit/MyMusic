package com.mymusic.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.mymusic.android.R;
import com.mymusic.android.domain.Topic;
import com.mymusic.android.util.ImageUtil;


/**
 * 好友列表adapter
 * Created by smile on 2018/5/26.
 */

public class TopicAdapter extends BaseQuickRecyclerViewAdapter<Topic> {

    public TopicAdapter(Context context, int layoutId) {
        super(context, layoutId);

    }

    @Override
    protected void bindData(ViewHolder holder, int position, final Topic data) {
        ImageUtil.show((Activity) context, (ImageView) holder.getView(R.id.iv_icon),data.getBanner());
        holder.setText(R.id.tv_title,"#"+data.getTitle()+"#");
        holder.setText(R.id.tv_info,context.getResources().getString(R.string.join_count,data.getJoins_count()));
    }

}
