package com.mymusic.android.parser;


import com.mymusic.android.parser.domain.LRCLyricsParser;
import com.mymusic.android.parser.domain.Lyric;

/**
 * Created by Cheng on 2018/6/23.
 * 歌词解析类
 */

public abstract class LyricsParser {
    protected final int type;
    protected final String content;
    protected Lyric lyric;

    protected LyricsParser(int type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * 根据类型，解析歌词
     *
     * @param type
     * @param content
     * @return
     */
    public static LyricsParser parse(int type, String content) {
        switch (type) {
            case Lyric.TYPE_LRC:
                return new LRCLyricsParser(type, content);
            case Lyric.TYPE_KSC:
                return new KSCLyricsParser(type, content);
            default:
                //其他类型，不支持直接返回null
                return null;
        }
    }

    public abstract void parse();

    public Lyric getLyric() {
        return lyric;
    }
}
