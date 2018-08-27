package com.mymusic.android.listener;


import com.mymusic.android.domain.Song;

/**
 * Created by smile on 2018/5/28.
 */

public interface PlayListListener {
    /**
     * 数据准备好了(歌词)，后面可能会用到其他数据
     */
    void onDataReady(Song song);

}
