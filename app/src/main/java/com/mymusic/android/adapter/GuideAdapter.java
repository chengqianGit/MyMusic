package com.mymusic.android.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mymusic.android.fragment.GuideFragment;


/**G
 * Created by Cheng on 03/06/2018.
 */

public class GuideAdapter extends BaseFragmentPagerAdapter<Integer> {
    public GuideAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(getData(position));
    }
}
