package com.mymusic.android.activity;

import android.app.DownloadManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mymusic.android.R;
import com.mymusic.android.domain.Song;
import com.mymusic.android.manager.MusicPlayerManager;
import com.mymusic.android.manager.PlayListManager;
import com.mymusic.android.parser.Line;
import com.mymusic.android.view.LyricView;
import com.mymusic.android.view.RecordThumbView;
import com.mymusic.android.view.RecordView;

import java.util.ArrayList;

public class MusicPlayerActivity extends BaseTitleActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnLongClickListener {

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

    /*
    text
     */
    private boolean isPlay;

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

    @Override
    protected void initListener() {
        super.initListener();
        iv_download.setOnClickListener(this);
        iv_play_control.setOnClickListener(this);
        iv_play_list.setOnClickListener(this);
        iv_loop_model.setOnClickListener(this);
        iv_previous.setOnClickListener(this);
        iv_next.setOnClickListener(this);
        sb_progress.setOnSeekBarChangeListener(this);

        rv.setOnClickListener(this);
        lv.setOnClickListener(this);
        sb_volume.setOnSeekBarChangeListener(this);

        lv.setOnLongClickListener(this);
        rv.setOnLongClickListener(this);

        //lv.setOnLyricClickListener(this);
        //playListManager.addPlayListListener(this);
    }

    private void stopRecordRotate() {
        rv.stopAlbumRotate();
        rt.stopThumbAnimation();
    }

    private void startRecordRotate() {
        rv.startAlbumRotate();
        rt.startThumbAnimation();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_control:
                playOrPause();
                break;
//            case R.id.iv_play_list:
//                showPlayListDialog();
//                break;
            case R.id.lv:
                lyric_container.setVisibility(View.GONE);
                rl_player_container.setVisibility(View.VISIBLE);
                break;
            case R.id.rv:
                lyric_container.setVisibility(View.VISIBLE);
                rl_player_container.setVisibility(View.GONE);
                break;
            case R.id.iv_previous:
                Song song = playListManager.previous();
                playListManager.play(song);
                break;
            case R.id.iv_next:
                Song songNext = playListManager.next();
                playListManager.play(songNext);
                break;
//            case R.id.iv_loop_model:
//                int loopModel = playListManager.changeLoopModel();
//                showLoopModel(loopModel);
//                break;
//            case R.id.iv_download:
//                download();
//                break;
        }
    }

    private void playOrPause() {
//        if (musicPlayerManager.isPlaying()) {
//            pause();
//        } else {
//            play();
//        }
        if (isPlay) {
            pause();
        } else {
            play();
        }
        isPlay = !isPlay;
    }

    private void play() {
        //musicPlayerManager.resume();
        startRecordRotate();
    }

    private void pause() {
        //musicPlayerManager.pause();
        stopRecordRotate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
