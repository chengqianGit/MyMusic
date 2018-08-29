package com.mymusic.android.adapter;

import android.content.Context;
import android.view.View;

import com.mymusic.android.R;
import com.mymusic.android.domain.Song;


/**
 * 播放列表adapter
 * Created by smile on 2018/5/26.
 */

public class PlayListAdapter extends BaseQuickRecyclerViewAdapter<Song> {

private OnRemoveClickListener onRemoveClickListener;
    private Song currentSong;

    public PlayListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void bindData(ViewHolder holder, final int position, Song data) {

        holder.setText(R.id.tv_title,String.format("%s - %s",data.getTitle(),data.getArtist_name()));
        if (data.equals(currentSong)) {
            holder.setTextColorRes(R.id.tv_title, R.color.main_color);
        } else {
            holder.setTextColorRes(R.id.tv_title,R.color.text);
        }

        holder.setOnClickListener(R.id.iv_remove,new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (onRemoveClickListener != null) {
                    onRemoveClickListener.onRemoveClick(position);
                }
            }
        });
    }

    public void setOnRemoveClickListener(OnRemoveClickListener onRemoveClickListener) {
        this.onRemoveClickListener = onRemoveClickListener;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public interface OnRemoveClickListener{
        void onRemoveClick(int position);
    }
}
