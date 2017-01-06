package com.ewebs.hotfood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ewebs.hotfood.MainActivity;

/**
 * Created by Paul on 2017/1/6.
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new MainActivity.Map();
            default:
                return MainActivity.PlaceholderFragment.newInstance(position + 1);

        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Map";
            case 1:
                return "Hot";
            case 2:
                return "Like";
            case 3:
                return "Search";
        }
        return null;
    }
}
