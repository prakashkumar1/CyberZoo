package com.twinvaves.darshan.darshantestproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class OptionsActivity extends ActionBarActivity implements View.OnClickListener {

    CheckBox SoundSwitch, AdultMode;
    public static boolean SoundOn, AdultModeOn;
    SharedPreferences prefs;
    RelativeLayout SoundRelativeLayout;
    TextView backgrounds;
    public static ImageView thumbNail;
    public static String SOUND = "SoundOn", PREFS = "prefs", ADULT_MODE = "adult_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        init();

    }

    private void init() {
        SoundRelativeLayout = (RelativeLayout) findViewById(R.id.SoundRelativeLayout);
        SoundSwitch = (CheckBox) findViewById(R.id.switchSound);
        AdultMode = (CheckBox) findViewById(R.id.AdultMode);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Rosemary.ttf");
        SoundSwitch.setTypeface(tf);
        AdultMode.setTypeface(tf);
        prefs = getSharedPreferences(PREFS, MODE_APPEND);

        SoundOn = prefs.getBoolean(SOUND, true);
        AdultModeOn = prefs.getBoolean(ADULT_MODE, true);
        backgrounds = (TextView) findViewById(R.id.tvBackgrounds);
        backgrounds.setTypeface(tf);
        backgrounds.setOnClickListener(this);
        SoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences(PREFS, MODE_APPEND);
                SharedPreferences.Editor editor = preferences.edit();
                if (isChecked) {
                    SoundOn = true;
                    editor.putBoolean(SOUND, true);
                    editor.commit();
                } else {
                    SoundOn = false;
                    editor.putBoolean(SOUND, false);
                    editor.commit();
                }
            }
        });
        AdultMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences(PREFS, MODE_APPEND);
                SharedPreferences.Editor editor = preferences.edit();
                if (isChecked) {
                    AdultModeOn = true;
                    editor.putBoolean(ADULT_MODE, true);
                    editor.commit();
                } else {
                    AdultModeOn = false;
                    editor.putBoolean(ADULT_MODE, false);
                    editor.commit();
                }
            }
        });
        SoundSwitch.setChecked(SoundOn);
        AdultMode.setChecked(AdultModeOn);
        thumbNail = (ImageView) findViewById(R.id.ivThumbnail);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), FirstScreen.mainBackground);
        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bm, 48, 48);
        thumbNail.setImageBitmap(thumbnail);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvBackgrounds) {
            Intent in = new Intent(OptionsActivity.this, SelectBackGroundActivity.class);
            startActivity(in);
        }
    }
}
