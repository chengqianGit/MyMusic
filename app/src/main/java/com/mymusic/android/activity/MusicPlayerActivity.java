package com.mymusic.android.activity;

import android.app.DownloadManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mymusic.android.R;
import com.mymusic.android.manager.MusicPlayerManager;
import com.mymusic.android.manager.PlayListManager;
import com.mymusic.android.parser.Line;
import com.mymusic.android.view.LyricView;
import com.mymusic.android.view.RecordThumbView;
import com.mymusic.android.view.RecordView;

import java.util.ArrayList;

public class MusicPlayerActivity extends BaseTitleActivity {

    private ImageView iv_loop_model;
    private ImageView iv_album_bg;
    private ImageView iv_play_control;
    private ImageView iv_play_list;
    private ImageView iv_previous;
    private ImageView iv_next;
    private TextView tv_start_time;
    private TextView tv_end_time;
    private SeekBar sb_progress;
    //歌词View
    private RecordThumbView rt;
    private ImageView iv_download;
    //唱片View
    private RecordView rv;
    private LinearLayout lyric_container;
    private RelativeLayout rl_player_container;
    private SeekBar sb_volume;
    private LyricView lv;

    private MusicPlayerManager musicPlayerManager;

    private AudioManager audioManager;
    private PlayListManager playListManager;
    //private PlayListDialogFragment playListDialog;

    private ArrayList<Line> currentLyricLines;
    //private LyricsParser parser;
    private DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
    }
    @Override
    protected void initViews() {
        super.initViews();
        enableBackMenu();

        iv_download = findViewById(R.id.iv_download);
        iv_album_bg = findViewById(R.id.iv_album_bg);
        iv_loop_model = findViewById(R.id.iv_loop_model);
        iv_play_control = findViewById(R.id.iv_play_control);
        rt = findViewById(R.id.rt);
        tv_start_time = findViewById(R.id.tv_start_time);
        tv_end_time = findViewById(R.id.tv_end_time);
        sb_progress = findViewById(R.id.sb_progress);
        iv_next = findViewById(R.id.iv_next);
        iv_previous = findViewById(R.id.iv_previous);
        iv_play_list = findViewById(R.id.iv_play_list);
        rv = findViewById(R.id.rv);
        lyric_container = findViewById(R.id.lyric_container);
        rl_player_container = findViewById(R.id.rl_player_container);
        sb_volume = findViewById(R.id.sb_volume);
        lv = findViewById(R.id.lv);
    }
}
