package com.mymusic.android;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mymusic.android.activity.BaseMusicPlayerActivity;
import com.mymusic.android.activity.BaseTitleActivity;
import com.mymusic.android.activity.LoginActivity;
import com.mymusic.android.activity.MessageActivity;
import com.mymusic.android.activity.MusicPlayerActivity;
import com.mymusic.android.activity.MyFriendActivity;
import com.mymusic.android.activity.SettingsActivity;
import com.mymusic.android.activity.UserDetailActivity;
import com.mymusic.android.adapter.HomeAdapter;
import com.mymusic.android.api.Api;
import com.mymusic.android.domain.User;
import com.mymusic.android.domain.response.DetailResponse;
import com.mymusic.android.event.LogoutSuccessEvent;
import com.mymusic.android.reactivex.HttpListener;
import com.mymusic.android.util.Consts;
import com.mymusic.android.util.UserUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseMusicPlayerActivity implements View.OnClickListener ,ViewPager.OnPageChangeListener {

    private DrawerLayout drawer;

    private static final String TAG = "TAG";
    ImageView iv_avatar;

    TextView tv_nickname;

    TextView tv_description;

    //首页放置的ViewPager,提供翻页效果
    ViewPager vp;
    private HomeAdapter adapter;
    private ImageView iv_music;
    private ImageView iv_recommend;
    private ImageView iv_video;

    private LinearLayout ll_settings;

    private LinearLayout ll_my_friend;
    private LinearLayout ll_message_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processIntent(getIntent());
    }

    @Override
    protected void initViews() {
        super.initViews();

        //也可以延迟注册，比如：当前用户点击到设置界面是才注册
        EventBus.getDefault().register(this);

        iv_avatar = findViewById(R.id.iv_avatar);
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_description = findViewById(R.id.tv_description);
        vp = findViewById(R.id.vp);

        iv_music = findViewById(R.id.iv_music);
        iv_recommend = findViewById(R.id.iv_recommend);
        iv_video = findViewById(R.id.iv_video);

        ll_settings = findViewById(R.id.ll_settings);
        ll_my_friend = findViewById(R.id.ll_my_friend);
        ll_message_container = findViewById(R.id.ll_message_container);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //缓存三个页面
        vp.setOffscreenPageLimit(3);

    }

    @Override
    protected void initDatas() {
        super.initDatas();

        //设置ViewPager适配器
        adapter = new HomeAdapter(getActivity(), getSupportFragmentManager());
        vp.setAdapter(adapter);

        ArrayList<Integer> datas = new ArrayList<>();
        datas.add(0);
        datas.add(1);
        datas.add(2);
        adapter.setDatas(datas);
        //显示用户信息
        showUserInfo();
    }

    private void showUserInfo() {
        //用户信息这部分，进来是看不到的，所以可以延后初始化
        if (sp.isLogin()) {
            //调用用户信息接口
            //将用户信息单独放一个工具类中为了以后能重用，其他地方同样需要显示用户信息
            Api.getInstance().userDetail(sp.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpListener<DetailResponse<User>>(getActivity()) {
                        @Override
                        public void onSucceeded(DetailResponse<User> data) {
                            super.onSucceeded(data);
                            showData(data.getData());
                        }
                    });

        } else {
            UserUtil.showNotLoginUser(getActivity(), iv_avatar, tv_nickname, tv_description);
        }
    }

    private void showData(User data) {
        //将显示用户信息放到单独的类中，是为了重用，因为在用户详情界面会用到
        UserUtil.showUser(getActivity(), data, iv_avatar, tv_nickname, tv_description);
    }

    //当点击用户头像时，跳转到用户详情界面
    public void avatarClick() {
        if (sp.isLogin()) {
            startActivityExtraId(UserDetailActivity.class, sp.getUserId());
        } else {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_avatar.setOnClickListener(this);
        //主页toolbar上三个图标的点击事件
        iv_music.setOnClickListener(this);
        iv_recommend.setOnClickListener(this);
        iv_video.setOnClickListener(this);

        ll_settings.setOnClickListener(this);

        ll_my_friend.setOnClickListener(this);
        ll_message_container.setOnClickListener(this);
        vp.addOnPageChangeListener(this);
        //默认选中第二个页面，设置监听器在选择就会调用监听器
        vp.setCurrentItem(1);
    }
    private void processIntent(Intent intent) {
        if (Consts.ACTION_MESSAGE.equals(intent.getAction())) {
            //要跳转到聊天界面
            String id = getIntent().getStringExtra(Consts.ID);
            //ConversationActivity.start(getActivity(),id);
        } else if (Consts.ACTION_MUSIC_PLAYER.equals(intent.getAction())) {
            startActivity(MusicPlayerActivity.class);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showUserInfo();
        processIntent(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_settings:
                startActivity(SettingsActivity.class);
                closeDrawer();
                break;
            case R.id.iv_music:
                vp.setCurrentItem(0, true);
                break;
            case R.id.iv_recommend:
                vp.setCurrentItem(1, true);
                break;
            case R.id.iv_video:
                vp.setCurrentItem(2, true);
                break;
            case R.id.iv_avatar:
                avatarClick();
                closeDrawer();
                break;
            case R.id.ll_my_friend:
                startActivity(MyFriendActivity.class);
                closeDrawer();
                break;
            case R.id.ll_message_container:
                startActivity(MessageActivity.class);
                closeDrawer();
                break;
            default:
                //如果当前界面没有处理，就调用父类的方法
                super.onClick(v);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutSuccessEvent(LogoutSuccessEvent event) {
        showUserInfo();
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            iv_music.setImageResource(R.drawable.ic_play_selected);
            iv_recommend.setImageResource(R.drawable.ic_music);
            iv_video.setImageResource(R.drawable.ic_video);
        } else if (position == 1) {
            iv_music.setImageResource(R.drawable.ic_play);
            iv_recommend.setImageResource(R.drawable.ic_music_selected);
            iv_video.setImageResource(R.drawable.ic_video);
        } else {
            iv_music.setImageResource(R.drawable.ic_play);
            iv_recommend.setImageResource(R.drawable.ic_music);
            iv_video.setImageResource(R.drawable.ic_video_selected);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void closeDrawer() {
        drawer.closeDrawer(Gravity.START);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
