package com.twinvaves.darshan.darshantestproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class StatisticsActivity extends ActionBarActivity {

    ViewPager mViewPager;
    ActionBar mActionBar;
    String items[] = {"One Player", "Two Players"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        init();

    }

    private void init() {
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = (ViewPager) findViewById(R.id.StatisticsViewPager);
        mViewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < items.length; i++) {
            mActionBar.addTab(
                    mActionBar.newTab()
                            .setText("" + items[i])
                            .setTabListener(new ActionBar.TabListener() {
                                @Override
                                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                                    mViewPager.setCurrentItem(tab.getPosition());
                                }

                                @Override
                                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                                }

                                @Override
                                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                                }
                            }));
        }
    }



    public class pagerAdapter extends FragmentStatePagerAdapter {

        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new OnePlayerStatisticsFragment();
                    return fragment;
                case 1:
                    fragment = new TwoPlayerStatisticsFragment();
                    return fragment;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return items.length;
        }
    }
}
