package com.mymusic.android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mymusic.android.MainActivity;
import com.mymusic.android.R;
import com.mymusic.android.util.Consts;
import com.mymusic.android.util.PackageUtil;

import wer.xds.fds.AdManager;
import wer.xds.fds.nm.sp.SplashViewSettings;
import wer.xds.fds.nm.sp.SpotListener;
import wer.xds.fds.nm.sp.SpotManager;
import wer.xds.fds.nm.sp.SpotRequestListener;

public class SplashActivity extends BaseCommonActivity {

    private static final String TAG ="TAG" ;
    private static final long DEFAULT_DELAY_TIME = 3000;
    public static final int MSG_GUIDE = 100;
    private static final int MSG_HOME = 110;
    //这样创建会有内存泄漏,later...
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GUIDE:
                    //跳转到引导页
                    startActivityAfterFinishThis(GuideActivity.class);
                    break;

                case MSG_HOME:
                    //跳转到首页
                    startActivityAfterFinishThis(MainActivity.class);
                    break;
            }
            //next();
        }
    };
    private ViewGroup ad_container;

    @Override
    protected void initViews() {
        super.initViews();

        ad_container = findViewById(R.id.ad_container);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        if (isShowGuide()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(MSG_GUIDE);
                }
            }, DEFAULT_DELAY_TIME);
        } else {
            //开始获取广告
            startGetAd();
            //startActivityAfterFinishThis(HomeActivity.class);
        }
        //延时3秒，在企业中通常会有很多逻辑处理，所以延时时间最好是用3-消耗的的时间
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(-1);
//            }
//        }, 2000);
    }

    /**
     * 收到消息，判断进入引导界面、主界面、或者登陆界面
     */
    private void next() {
        if (isShowGuide()) {
            //第一次需要引导界面，然后进入引导界面里面，完成逻辑后修改version属性，下次不在进入引导界面
            startActivityAfterFinishThis(GuideActivity.class);
        } else if (sp.isLogin()) {
            startActivityAfterFinishThis(MainActivity.class);
        } else {
            startActivityAfterFinishThis(LoginActivity.class);
        }
    }
    /**
     * 根据当前版本号判断是否需要引导页
     * @return
     */
    private boolean isShowGuide() {
        return sp.getBoolean(String.valueOf(PackageUtil.getVersionCode(getApplicationContext())),true);
    }

    private void startGetAd() {
        AdManager.getInstance(this).init(Consts.AK, Consts.AS, false);
        SpotManager.getInstance(this).requestSpot(new SpotRequestListener() {
            @Override
            public void onRequestSuccess() {
                Log.d(TAG, "onRequestSuccess");
                //广告获取成功，显示广告
                showA();
            }

            @Override
            public void onRequestFailed(int i) {
                Log.d(TAG, "onRequestFailed:" + i);
                toHome();

            }
        });
    }
    private void toHome() {
        //获取失败，延时3秒进入主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_HOME);
            }
        }, DEFAULT_DELAY_TIME);
    }
    private void showA() {
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        splashViewSettings.setTargetClass(MainActivity.class);
        splashViewSettings.setSplashViewContainer(ad_container);
        SpotManager.getInstance(this).showSplash(this,
                splashViewSettings, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Log.d(TAG, "onShowSuccess");
                    }

                    @Override
                    public void onShowFailed(int i) {
                        Log.d(TAG, "onShowFailed: " + i);

                        //如果显示的时候失败，还是进入主界面
                        toHome();
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.d(TAG, "onSpotClosed");
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        Log.d(TAG, "onSpotClicked");
                    }
                });
    }
}
