package com.mymusic.android.util;

import android.util.Log;

import com.mymusic.android.BuildConfig;


/**
 * Created by Cheng on 06/06/2018.
 * 定义自己的日志工具
 */

public class LogUtil {
    public static final boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "TAG";

    public static void d(String tag, String l) {
        if (isDebug) {
            Log.d(tag, l);
        }
    }

    public static void d(String tag, int msg) {
        if (isDebug) {
            Log.d(tag, msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String tag, boolean b) {
        if (isDebug) {
            Log.e(TAG, b + "");
        }
    }
}
