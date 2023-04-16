package com.example.coolnewsapp.ui.main;

import android.content.Context;
import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.coolnewsapp.R;

import java.util.ArrayList;
import java.util.List;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private List<Pair<Fragment, String>> fragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new Pair(PlaceholderFragment.newInstance(true), "India"));
        fragments.add(new Pair(CountryFragment.newInstance(true), "Global"));
        mContext = context;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).first;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).second;
    }
}