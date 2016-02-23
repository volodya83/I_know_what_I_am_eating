package com.peter.vladimir.iknowwhatiameating;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class TodayFragmentPagerAdapter extends FragmentPagerAdapter {
    public TodayFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        ToDay_Fragment toDay_fragment  = new ToDay_Fragment();
        Bundle data = new Bundle();
        data.putInt("current_page", i+1);
        toDay_fragment.setArguments(data);
        return toDay_fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
