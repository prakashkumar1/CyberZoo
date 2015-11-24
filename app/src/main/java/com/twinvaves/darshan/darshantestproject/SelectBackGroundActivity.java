package com.twinvaves.darshan.darshantestproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectBackGroundActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_back_ground);
        init();
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.backgroundsViewPager);
        mViewPager.setAdapter(new CustomPagerAdapter(getApplicationContext()));

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FirstScreen.mainBackground = FirstScreen.backgrounds[position];
        Toast.makeText(getApplicationContext(), "Background " + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
        SharedPreferences mSharedPreferences = getSharedPreferences(FirstScreen.POINTS_PREFS, MODE_APPEND);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(FirstScreen.BACK_GROUND, FirstScreen.backgrounds[position]);
        editor.commit();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), FirstScreen.mainBackground);
        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bm, 48, 48);
        OptionsActivity.thumbNail.setImageBitmap(thumbnail);

    }


    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return FirstScreen.backgrounds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (RelativeLayout) object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View itemView = mLayoutInflater.inflate(R.layout.fragment_background_view, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageBackground);
            imageView.setImageResource(FirstScreen.backgrounds[position]);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirstScreen.mainBackground = FirstScreen.backgrounds[position];
                    Toast.makeText(getApplicationContext(), "Background " + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
                    SharedPreferences mSharedPreferences = getSharedPreferences(FirstScreen.POINTS_PREFS, MODE_APPEND);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putInt(FirstScreen.BACK_GROUND, FirstScreen.backgrounds[position]);
                    editor.commit();
                    Bitmap bm = BitmapFactory.decodeResource(getResources(), FirstScreen.mainBackground);
                    Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bm, 48, 48);
                    OptionsActivity.thumbNail.setImageBitmap(thumbnail);
                }
            });
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Background " + (position + 1) + "";

        }
    }
}

