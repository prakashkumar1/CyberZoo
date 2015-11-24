package com.twinvaves.darshan.darshantestproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FirstScreen extends ActionBarActivity implements AdapterView.OnItemClickListener {

    //    ButtonRectangle onePlayer, twoPlayer, statistics, options, share, quit;
    String[] items = {"One Player", "Two Players", "Scores", "Settings", "Tell your friends", "Quit"};
    int[] drawables = {R.drawable.single_player, R.drawable.two_players, R.drawable.stats, R.drawable.settings
            , R.drawable.share, R.drawable.close};
    public static String
            POINTS_PREFS = "points_prefs", POINTS_WON = "points_won", POINTS_LOST = "points_lost",
            POINTS_PLAYED = "points_played", POINTS_DRAW = "points_draw", WIN_PERCENT = "win_percent",
            BACK_GROUND = "back_ground";
    public static final int RIPPLE_SPEED = 75;
    public static int[] backgrounds = {R.drawable.back_ground_1, R.drawable.back_ground_2
            , R.drawable.back_ground_3, R.drawable.back_ground_4};
    public static int mainBackground = R.drawable.back_ground_1;
    ListView lv;
    public static String newPlayerName = null, newCompName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        init();
        getPoints();

    }

    private void getPoints() {
        SharedPreferences mSharedPreferences = getSharedPreferences(POINTS_PREFS, MODE_APPEND);
        OnePlayerStatisticsFragment.played = mSharedPreferences.getInt(POINTS_PLAYED, 0);
        OnePlayerStatisticsFragment.won = mSharedPreferences.getInt(POINTS_WON, 0);
        OnePlayerStatisticsFragment.lost = mSharedPreferences.getInt(POINTS_LOST, 0);
        OnePlayerStatisticsFragment.draw = mSharedPreferences.getInt(POINTS_DRAW, 0);
        OnePlayerStatisticsFragment.winPercent = mSharedPreferences.getFloat(WIN_PERCENT, 0);
        mainBackground = mSharedPreferences.getInt(BACK_GROUND, R.drawable.back_ground_1);
        SharedPreferences prefs = getSharedPreferences(OptionsActivity.PREFS, MODE_APPEND);
        OptionsActivity.SoundOn = prefs.getBoolean(OptionsActivity.SOUND, true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LayoutInflater mLayoutInflater = getLayoutInflater();
        ReadData mReadData = new ReadData(getApplicationContext(), mLayoutInflater);
        if (mReadData.fileExistance(WritingData.FILE_NAME)) {
            mReadData.execute(getApplicationContext());
        }
    }

    private void init() {
        lv = (ListView) findViewById(R.id.listScreen);
        lv.setAdapter(new myCustomadapter());
        lv.setOnItemClickListener(this);

//        onePlayer = (ButtonRectangle) findViewById(R.id.OnePlayer);
//        twoPlayer = (ButtonRectangle) findViewById(R.id.TwoPlayer);
//        statistics = (ButtonRectangle) findViewById(R.id.statistics);
//        options = (ButtonRectangle) findViewById(R.id.options);
//        share = (ButtonRectangle) findViewById(R.id.share);
//        quit = (ButtonRectangle) findViewById(R.id.quit);
//        share.setOnClickListener(this);
//        quit.setOnClickListener(this);
//        options.setOnClickListener(this);
//        onePlayer.setTypeFace(tf);
//        twoPlayer.setTypeFace(tf);
//        statistics.setTypeFace(tf);
//        options.setTypeFace(tf);
//        share.setTypeFace(tf);
//        quit.setTypeFace(tf);
//        onePlayer.setOnClickListener(this);
//        twoPlayer.setOnClickListener(this);
//        statistics.setOnClickListener(this);
//        options.setOnClickListener(this);
//        onePlayer.setRippleSpeed(RIPPLE_SPEED);
//        twoPlayer.setRippleSpeed(RIPPLE_SPEED);
//        options.setRippleSpeed(RIPPLE_SPEED);
//        statistics.setRippleSpeed(RIPPLE_SPEED);
//        share.setRippleSpeed(RIPPLE_SPEED);
//        quit.setRippleSpeed(RIPPLE_SPEED);
    }


    private void callShare() {
//        SharedPreferences prefs = getSharedPreferences("checkShare", MODE_APPEND);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putInt("shared", 1);
//        editor.commit();
        Intent in = new Intent(Intent.ACTION_SEND);
        in.setType("text/plain");
        String title = "I enjoyed the game \"TicTacZoo \", pls check out - ", message = "https://play.google.com/store/apps/details?id=com.twinvaves.darshan.darshantestproject";
//        in.putExtra(Intent.EXTRA_SUBJECT, title);
        in.putExtra(Intent.EXTRA_TEXT, title+message);
        startActivity(in);

    }

    @Override
    public void onBackPressed() {

    }

    public class myCustomadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Rosemary.ttf");
            LayoutInflater inflater = getLayoutInflater();
            convertView = inflater.inflate(R.layout.single_option, parent, false);
            ImageView iconImage = (ImageView) convertView.findViewById(R.id.iconImage);
            TextView tvOption = (TextView) convertView.findViewById(R.id.tvOption);
            iconImage.setImageResource(drawables[position]);
            tvOption.setText(items[position]);
            tvOption.setTypeface(tf);


            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in;
        switch (position) {
            case 0:
                in = new Intent(FirstScreen.this, Categories.class);
                in.putExtra("getWhich", 1);
                startActivity(in);
                break;
            case 1:
                in = new Intent(FirstScreen.this, Categories.class);
                in.putExtra("getWhich", 2);
                startActivity(in);
                break;
            case 2:
                in = new Intent(FirstScreen.this, StatisticsActivity.class);
                startActivity(in);
                break;
            case 3:
                in = new Intent(FirstScreen.this, OptionsActivity.class);
                startActivity(in);
                break;
            case 4:
                callShare();
                break;
            case 5: {
                Toast.makeText(getApplicationContext(), "Thank You for playing Cyber Zoo, please share it with your friends ", Toast.LENGTH_SHORT).show();
                super.onBackPressed();

            }
            break;


        }
    }

   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.OnePlayer:

                break;
            case R.id.TwoPlayer:

                break;
            case R.id.statistics:

                break;
            case R.id.options:

                break;
            case R.id.share:

                break;
            case R.id.quit:

                break;
        }*/
}

