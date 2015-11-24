package com.twinvaves.darshan.darshantestproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;


public class Categories extends ActionBarActivity {
    public static final String PLAYER_1 = "player1", PLAYER_2 = "player2";
    public static final String PLAYER_1_NAME = "player_1_name", PLAYER_2_NAME = "player_2_name";
    public static final String PLAYER_1_SOUND = "player_1_sound", PLAYER_2_SOUND = "player_2_sound";
    public static final String ACTUAL_NAME_1 = "actual_name_1", ACTUAL_NAME_2 = "actual_name_2";
    public static final String PLAYER_1_POINTS = "player1points", PLAYER_2_POINTS = "player2points";

    ViewPager mViewPager;
    ImageView imageView;
    ActionBar mActionBar;
    public static int which, playerOnePoints, playerTwoPoints;
    public static String p1Name, p2Name;
    String[] categoryNames = {"Birds", "Animals", "Insects", "Reptiles", "Mammals"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Intent in = getIntent();
        which = in.getIntExtra("getWhich", 0);
        if (which == 1) {
            p1Name = "player";
            p2Name = "mobile";
            mainApplication.PLAYERNAME = "player";
            mainApplication.COMPUTERNAME = "mobile";
           /* if (FirstScreen.newPlayerName == null || FirstScreen.newCompName == null) {
                FirstScreen.newPlayerName = "player";
                FirstScreen.newCompName = "mobile";
            }*/

        } else if (which == 2) {
            p1Name = "player 1";
            p2Name = "player 2";

            mainApplication.PLAYERNAME = "player 1";
            mainApplication.COMPUTERNAME = "player 2";
            /*if (FirstScreen.newPlayerName == null || FirstScreen.newCompName == null) {
                FirstScreen.newPlayerName = "player 1";
                FirstScreen.newCompName = "player 2";
            }*/
        }
        if (in != null) {
            playerOnePoints = in.getIntExtra(PLAYER_1_POINTS, -1);
            playerTwoPoints = in.getIntExtra(PLAYER_2_POINTS, -1);
            p1Name = in.getStringExtra(PLAYER_1_NAME);
            p2Name = in.getStringExtra(PLAYER_2_NAME);
            mainApplication.PLAYERNAME = in.getStringExtra("PLAYERNAME");
            mainApplication.COMPUTERNAME = in.getStringExtra("COMPUTERNAME");
        }

        init();
    }

    private void init() {
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = (ViewPager) findViewById(R.id.pager);
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
        for (int i = 0; i < categoryNames.length; i++) {
            mActionBar.addTab(
                    mActionBar.newTab()
                            .setText("" + categoryNames[i])
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
                    fragment = new birds_fragment();
                    return fragment;
                case 1:
                    fragment = new AnimalsFragment();
                    return fragment;
                case 2:
                    fragment = new InsectsFragment();
                    return fragment;
                case 3:
                    fragment = new ReptilesFragment();
                    return fragment;
                case 4:
                    fragment = new MammalsFragment();
                    return fragment;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return categoryNames.length;
        }
    }
}
