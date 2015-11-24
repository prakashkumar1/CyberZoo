package com.twinvaves.darshan.darshantestproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.ButtonRectangle;
import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.Random;


public class OnePlayerActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView ivMain;
    ImageView iv[] = new ImageView[9];
    boolean[] mainCount = {false, false, false, false, false, false, false, false, false};
    boolean[] playerCount = {false, false, false, false, false, false, false, false, false};
    boolean[] compCount = {false, false, false, false, false, false, false, false, false};
    boolean switchC = false;
    int playerImage, compImage, playerSound, compSound;
    String playerName, compName;
    public String ActualPlayerName, ActualCompName;
    static int winPlayer = 0;
    private ButtonRectangle player1, player2;
    private RippleView mainView;
    private TextView points1, points2;
    private int player1points, player2points;
    private boolean animationRunning = false;
    boolean wonTheMatch = false;
    int temp = 0;
    int timesClicked = 0;
    static int playerClickedButton = 0, compClickedButton = 0;
    boolean compCombination1, compCombination2, compCombination3, compCombination4, compCombination5, compCombination6, compCombination7, compCombination8;
    boolean playerCombination1, playerCombination2, playerCombination3, playerCombination4, playerCombination5, playerCombination6, playerCombination7, playerCombination8;

    MediaPlayer mp;
    Techniques[] main = {Techniques.ZoomOutLeftUp, Techniques.ZoomOutUp, Techniques.ZoomOutRightUp, Techniques.ZoomOutLeft,
            Techniques.ZoomOut, Techniques.ZoomOutRight, Techniques.ZoomOutLeftDown, Techniques.ZoomOutDown, Techniques.ZoomOutRightDown};
    Techniques[] sub = {Techniques.FadeInDown, Techniques.FadeInDown, Techniques.FadeInDown, Techniques.FadeInLeft, Techniques.FadeIn,
            Techniques.FadeInRight, Techniques.FadeInUp, Techniques.FadeInUp, Techniques.FadeInUp};

    int[][][] b = {
            {
                    {1, 2},
                    {3, 6},
                    {4, 8}
            },
            {
                    {0, 2},
                    {4, 7}
            },
            {
                    {0, 1},
                    {5, 8},
                    {4, 6}
            },
            {
                    {0, 6},
                    {4, 5}
            },
            {
                    {3, 5},
                    {1, 7},
                    {0, 8},
                    {2, 6}
            },
            {
                    {2, 8},
                    {3, 4}
            },
            {
                    {0, 3},
                    {7, 8},
                    {2, 4}
            },
            {
                    {1, 4},
                    {6, 8}
            },
            {
                    {2, 5},
                    {6, 7},
                    {0, 4}
            }
    };
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            restore(savedInstanceState);
        }
        GetIntent();
        init();
        changeColor(1);

    }


    private void GetIntent() {
        Intent intent = getIntent();
        playerImage = intent.getIntExtra(Categories.PLAYER_1, -1);
        compImage = intent.getIntExtra(Categories.PLAYER_2, -1);
        playerName = intent.getStringExtra(Categories.PLAYER_1_NAME);
        compName = intent.getStringExtra(Categories.PLAYER_2_NAME);
        ActualPlayerName = intent.getStringExtra(Categories.ACTUAL_NAME_1);
        ActualCompName = intent.getStringExtra(Categories.ACTUAL_NAME_2);
        playerSound = intent.getIntExtra(Categories.PLAYER_1_SOUND, -1);
        compSound = intent.getIntExtra(Categories.PLAYER_2_SOUND, -1);
        if (Categories.playerOnePoints != -1 && Categories.playerTwoPoints != -1) {
            player1points = Categories.playerOnePoints;
            player2points = Categories.playerTwoPoints;
        } else {
            player1points = 0;
            player2points = 0;
        }

    }

    private void init() {
        ivMain = (ImageView) findViewById(R.id.mainImage);
        iv[0] = (ImageView) findViewById(R.id.iv1);
        iv[1] = (ImageView) findViewById(R.id.iv2);
        iv[2] = (ImageView) findViewById(R.id.iv3);
        iv[3] = (ImageView) findViewById(R.id.iv4);
        iv[4] = (ImageView) findViewById(R.id.iv5);
        iv[5] = (ImageView) findViewById(R.id.iv6);
        iv[6] = (ImageView) findViewById(R.id.iv7);
        iv[7] = (ImageView) findViewById(R.id.iv8);
        iv[8] = (ImageView) findViewById(R.id.iv9);

        iv[0].setOnClickListener(this);
        iv[1].setOnClickListener(this);
        iv[2].setOnClickListener(this);
        iv[3].setOnClickListener(this);
        iv[4].setOnClickListener(this);
        iv[5].setOnClickListener(this);
        iv[6].setOnClickListener(this);
        iv[7].setOnClickListener(this);
        iv[8].setOnClickListener(this);


        player1 = (ButtonRectangle) findViewById(R.id.player1);
        player2 = (ButtonRectangle) findViewById(R.id.player2);
        mainView = (RippleView) findViewById(R.id.more);
        mainView.setBackgroundResource(FirstScreen.mainBackground);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Rosemary.ttf");
        player1.setTypeFace(tf);
        player2.setTypeFace(tf);
        player1.setText("" + playerName);
        player1.setRippleSpeed(50);
        player2.setText("" + compName);
        player2.setRippleSpeed(50);

        points1 = (TextView) findViewById(R.id.tvPoints1);
        points2 = (TextView) findViewById(R.id.tvPoints2);
        points1.setText("" + player1points);
        points2.setText("" + player2points);

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerClickedButton == 0) {
                    playerClickedButton = 1;
                    if (mainApplication.PLAYERNAME.equals(null) || mainApplication.PLAYERNAME.equals("")) {
                        player1.setText("player");
                    } else {
                        Log.d("RAJ", "comp name " + mainApplication.PLAYERNAME);
                        player1.setText("" + mainApplication.PLAYERNAME);
                    }
                } else if (playerClickedButton == 1) {
                    playerClickedButton = 0;
                    player1.setText("" + playerName);
                }

            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (compClickedButton == 0) {
                    compClickedButton = 1;
                    if (mainApplication.COMPUTERNAME.equals(null) || mainApplication.COMPUTERNAME.equals("")) {
                        player2.setText("mobile");
                    } else {
                        Log.d("RAJ", "comp name " + mainApplication.COMPUTERNAME);
                        player2.setText("" + mainApplication.COMPUTERNAME);
                    }
                } else if (compClickedButton == 1) {
                    compClickedButton = 0;
                    player2.setText("" + compName);
                }*/
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.twinvave.godiswithme"));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {

        if (animationRunning == false) {
            switch (v.getId()) {
                case R.id.iv1:
                    playerClicked(0);
                    break;
                case R.id.iv2:
                    playerClicked(1);
                    break;
                case R.id.iv3:
                    playerClicked(2);
                    break;
                case R.id.iv4:
                    playerClicked(3);
                    break;
                case R.id.iv5:
                    playerClicked(4);
                    break;
                case R.id.iv6:
                    playerClicked(5);
                    break;
                case R.id.iv7:
                    playerClicked(6);
                    break;
                case R.id.iv8:
                    playerClicked(7);
                    break;
                case R.id.iv9:
                    playerClicked(8);
                    break;
            }
        }

    }

    private void playerClicked(int i) {
        if (mainCount[i]) {
            Toast.makeText(OnePlayerActivity.this, "Already Clicked", Toast.LENGTH_SHORT).show();
        } else {
            animationRunning = true;
            mainCount[i] = true;
            playerCount[i] = true;
            timesClicked++;
            playCustomAnimation(i);
        }
    }

    private void playCustomAnimation(final int i) {
        YoYo.with(main[i]).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivMain.setBackgroundResource(playerImage);
                ivMain.setVisibility(View.VISIBLE);
                playSound(playerSound);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv[i].setBackgroundResource(playerImage);
                YoYo.with(sub[i]).duration(500).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationRunning = false;
                        int check = checkForGame();
                        changeColor(0);
                        if (check == -1) {
                            callCompPlay();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(iv[i]);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(1500).playOn(ivMain);
    }


    private int checkForGame() {
        if (OptionsActivity.SoundOn) {
            if (mp != null) {
                mp.reset();
                Log.d("mediaplayer", "reset");
            }
        }
        compCombination1 = (compCount[0] && compCount[1] && compCount[2]);
        compCombination2 = (compCount[3] && compCount[4] && compCount[5]);
        compCombination3 = (compCount[6] && compCount[7] && compCount[8]);
        compCombination4 = (compCount[0] && compCount[3] && compCount[6]);
        compCombination5 = (compCount[1] && compCount[4] && compCount[7]);
        compCombination6 = (compCount[2] && compCount[5] && compCount[8]);
        compCombination7 = (compCount[0] && compCount[4] && compCount[8]);
        compCombination8 = (compCount[2] && compCount[4] && compCount[6]);
        if (compCombination1 || compCombination2 || compCombination3
                || compCombination4 || compCombination5 || compCombination6
                || compCombination7 || compCombination8) {
            winPlayer = 1;
            if (wonTheMatch == false) wonTheMatch = true;
            else wonTheMatch = false;
        }

        playerCombination1 = (playerCount[0] && playerCount[1] && playerCount[2]);
        playerCombination2 = (playerCount[3] && playerCount[4] && playerCount[5]);
        playerCombination3 = (playerCount[6] && playerCount[7] && playerCount[8]);
        playerCombination4 = (playerCount[0] && playerCount[3] && playerCount[6]);
        playerCombination5 = (playerCount[1] && playerCount[4] && playerCount[7]);
        playerCombination6 = (playerCount[2] && playerCount[5] && playerCount[8]);
        playerCombination7 = (playerCount[0] && playerCount[4] && playerCount[8]);
        playerCombination8 = (playerCount[2] && playerCount[4] && playerCount[6]);
        if (
                playerCombination1 || playerCombination2 || playerCombination3
                        || playerCombination4 || playerCombination5
                        || playerCombination6 || playerCombination7
                        || playerCombination8) {
            winPlayer = 2;
            if (wonTheMatch == false) wonTheMatch = true;
            else wonTheMatch = false;

        }

        if (winPlayer == 1) {
            int[] who = checkWhichCompComboWon();
            playWinAnimation(who, 2);
//            WinDialog(2);
            return 1;
        } else if (winPlayer == 2) {
            int[] who = checkWhichPlayerComboWon();
            playWinAnimation(who, 1);
//            WinDialog(1);
            return 1;
        } else if (mainCount[0] && mainCount[1] && mainCount[2] && mainCount[3] && mainCount[4] && mainCount[5] && mainCount[6] && mainCount[7] && mainCount[8]) {
            if (wonTheMatch == false) wonTheMatch = true;
            else wonTheMatch = false;
            playDrawAnimation(-1);
//            WinDialog(-1);
            return 1;
        }

        return -1;
    }

    private void playDrawAnimation(final int i) {
        animationRunning = true;
        for (int jo = 0; jo < 8; jo++) {
            YoYo.with(Techniques.Shake).duration(1000).playOn(iv[jo]);
        }
        YoYo.with(Techniques.Shake).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                WinDialog(i);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(1000).playOn(iv[8]);

    }

    private void playWinAnimation(int[] who, final int winner) {
        animationRunning = true;
        YoYo.with(Techniques.Shake).duration(1000).playOn(iv[who[0]]);
        YoYo.with(Techniques.Shake).duration(1000).playOn(iv[who[1]]);
        YoYo.with(Techniques.Shake).duration(1000).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                WinDialog(winner);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(iv[who[2]]);

    }

    private int[] checkWhichPlayerComboWon() {
        int[] wonCombi = new int[3];
        if (playerCombination1) {
            wonCombi[0] = 0;
            wonCombi[1] = 1;
            wonCombi[2] = 2;
            return wonCombi;

        } else if (playerCombination2) {
            wonCombi[0] = 3;
            wonCombi[1] = 4;
            wonCombi[2] = 5;
            return wonCombi;

        } else if (playerCombination3) {
            wonCombi[0] = 6;
            wonCombi[1] = 7;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (playerCombination4) {
            wonCombi[0] = 0;
            wonCombi[1] = 3;
            wonCombi[2] = 6;
            return wonCombi;

        } else if (playerCombination5) {
            wonCombi[0] = 1;
            wonCombi[1] = 4;
            wonCombi[2] = 7;
            return wonCombi;

        } else if (playerCombination6) {
            wonCombi[0] = 2;
            wonCombi[1] = 5;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (playerCombination7) {
            wonCombi[0] = 0;
            wonCombi[1] = 4;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (playerCombination8) {
            wonCombi[0] = 2;
            wonCombi[1] = 4;
            wonCombi[2] = 6;
            return wonCombi;

        }
        return wonCombi;

    }

    private int[] checkWhichCompComboWon() {
        int[] wonCombi = new int[3];
        if (compCombination1) {
            wonCombi[0] = 0;
            wonCombi[1] = 1;
            wonCombi[2] = 2;
            return wonCombi;

        } else if (compCombination2) {
            wonCombi[0] = 3;
            wonCombi[1] = 4;
            wonCombi[2] = 5;
            return wonCombi;

        } else if (compCombination3) {
            wonCombi[0] = 6;
            wonCombi[1] = 7;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (compCombination4) {
            wonCombi[0] = 0;
            wonCombi[1] = 3;
            wonCombi[2] = 6;
            return wonCombi;

        } else if (compCombination5) {
            wonCombi[0] = 1;
            wonCombi[1] = 4;
            wonCombi[2] = 7;
            return wonCombi;

        } else if (compCombination6) {
            wonCombi[0] = 2;
            wonCombi[1] = 5;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (compCombination7) {
            wonCombi[0] = 0;
            wonCombi[1] = 4;
            wonCombi[2] = 8;
            return wonCombi;

        } else if (compCombination8) {
            wonCombi[0] = 2;
            wonCombi[1] = 4;
            wonCombi[2] = 6;
            return wonCombi;

        }
        return wonCombi;
    }

    private void WinDialog(final int i) {
        String title = null;
        if (wonTheMatch) {
            if (i != -1) {
                if (i == 1) {
                    title = "" + playerName + " won!";
                    OnePlayerStatisticsFragment.played++;
                    OnePlayerStatisticsFragment.won++;
                } else if (i == 2) {
                    title = "" + compName + " won!";
                    OnePlayerStatisticsFragment.played++;
                    OnePlayerStatisticsFragment.lost++;
                }
            } else {
                title = "It's a tie!!";
                OnePlayerStatisticsFragment.played++;
                OnePlayerStatisticsFragment.draw++;
            }
            if (i == 1) {
                if (player1points == 4) {
                    mp = MediaPlayer.create(this, R.raw.clap);
                    mp.start();
                } else if (player1points == 9) {
                    mp = MediaPlayer.create(this, R.raw.gallery);
                    mp.start();
                }
            } else if (i == 2) {
                if (player2points == 4) {
                    mp = MediaPlayer.create(this, R.raw.clap);
                    mp.start();
                } else if (player2points == 9) {
                    mp = MediaPlayer.create(this, R.raw.gallery);
                    mp.start();
                }
            }
            new AlertDialog.Builder(this)
                    .setMessage(title)
                    .setIcon(R.drawable.ic_winner)
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                            dialog.dismiss();
                            notifyPoints(i);
                        }
                    })
                    .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                            OnePlayerActivity.this.finish();


                        }
                    })
                    .setNeutralButton("Change Pair", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                            if (i == 1) {
                                player1points++;
                            } else if (i == 2) {
                                player2points++;
                            }
                            Intent in = new Intent(OnePlayerActivity.this, Categories.class);
                            in.putExtra("getWhich", 1);
                            in.putExtra(Categories.PLAYER_1_POINTS, player1points);
                            in.putExtra(Categories.PLAYER_2_POINTS, player2points);
                            in.putExtra("PLAYERNAME", mainApplication.PLAYERNAME);
                            in.putExtra("COMPUTERNAME", mainApplication.COMPUTERNAME);
                            startActivity(in);
                            finish();
                        }
                    })
                    .setTitle("Game Complete").setCancelable(false)
                    .show();
        }

    }

    private void notifyPoints(int who) {
        reset();
        if (who == 1) {
            player1points++;
            YoYo.with(Techniques.BounceIn).duration(500).playOn(points1);
            points1.setText("" + player1points);
        } else if (who == 2) {
            player2points++;
            YoYo.with(Techniques.BounceIn).duration(500).playOn(points2);
            points2.setText("" + player2points);
        } else if (who == -1) {
            YoYo.with(Techniques.RubberBand).duration(500).playOn(points1);
            YoYo.with(Techniques.RubberBand).duration(500).playOn(points2);
        }
    }


    private void playSound(int sound) {
        if (OptionsActivity.SoundOn) {
//            Log.d("Sound", "" + OptionsActivity.SoundOn);
            try {
                mp = MediaPlayer.create(getApplicationContext(), sound);
                mp.start();
                Log.d("mediaplayer", "start");
            } catch (Exception e) {
                Log.d("error in mp", " " + e);
                Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
            }


        }
    }


    private void changeColor(int which) {

        if (which == 0) {
            player1.setBackgroundColor(getResources().getColor(R.color.white));
            player1.setTextColor(getResources().getColor(R.color.buttonText));
            player2.setTextColor(getResources().getColor(R.color.white));
            player2.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        } else if (which == 1) {
            player1.setBackgroundColor(getResources().getColor(R.color.buttonColor));
            player1.setTextColor(getResources().getColor(R.color.white));
            player2.setTextColor(getResources().getColor(R.color.buttonText));
            player2.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private void reset() {

        switchC = false;
        winPlayer = 0;
        temp = 0;
        wonTheMatch = false;
        timesClicked = 0;
        animationRunning = false;
        changeColor(1);
        ivMain.setBackgroundResource(0);
        for (int a = 0; a < 9; a++) {
            mainCount[a] = false;
            playerCount[a] = false;
            compCount[a] = false;
            iv[a].setBackgroundResource(R.color.imageDefault);
        }
        if (mp != null) {
            mp.reset();
            mp.release();
            mp = null;
            Log.d("mediaPlayer", "mp released");
        } else {
            Log.d("mediaPlayer", "mp is " + mp);

        }
    }

    private int checkOne() {
        int firstIndex = 0;
        for (int i = 0; i < 9; i++) {
            if (playerCount[i]) {
                firstIndex = i;//getting player's animal index
                break;
            } else firstIndex = -1;
        }
        // get a random second index
        int secondIndex = rand.nextInt(b[firstIndex].length);
        // get a random third index
        int thirdIndex = rand.nextInt(b[firstIndex][secondIndex].length);
        //return the random position for the comp animal
        return b[firstIndex][secondIndex][thirdIndex];
    }

    private int checkTwo() {
        //get 2 indices of the player's animals in the array 'index'
        int[] index = new int[2];
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (playerCount[i]) {
                index[counter] = i;
                counter++;
            }
        }
        //using first index, find the element in 'b' array
        // with second element of subarray as the 2nd index found
        // when 2 nos match , ret the remaining number
        for (int i = 0; i < b[index[0]].length; i++) {
            for (int j = 0; j < 2; j++) {
                if (b[index[0]][i][j] == index[1]) {
                    if (j == 0)
                        return b[index[0]][i][1];
                    else
                        return b[index[0]][i][0];
                }
            }
        }
        // if nothing matches ret the first unfilled box
        //changed it to random
        Random random = new Random();
        ArrayList<Integer> not_filled = new ArrayList();
        for (int k = 0; k < 9; k++) {
            if (!mainCount[k]) {
                not_filled.add(k);
            }
        }
        int rand_index = random.nextInt(not_filled.size());
        return  not_filled.get(rand_index);
    }


    private int checkThree() {

        //first attack for 2 comp animals
        Log.d("DARSHANROHAN", "" + OptionsActivity.AdultModeOn);
        if (OptionsActivity.AdultModeOn) {
            int[] a_index = new int[2];
            int a_counter = 0;
            for (int i = 0; i < 9; i++) {
                if (compCount[i]) {
                    a_index[a_counter] = i;
                    a_counter++;
                }
            }
            //using first index, find the element in 'b' array
            // with second element of subarray as the 2nd index found
            // when 2 nos match , ret the remaining number
            for (int i = 0; i < b[a_index[0]].length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (b[a_index[0]][i][j] == a_index[1]) {
                        if (j == 0) {
                            if (!mainCount[b[a_index[0]][i][1]])
                                return b[a_index[0]][i][1];
                        } else {
                            if (!mainCount[b[a_index[0]][i][0]])
                                return b[a_index[0]][i][0];
                        }
                    }
                }
            }
        }

        //defend the boxes if can't win

        int[] d_index = new int[3];
        int d_counter = 0;
        //get all three positions of player's animals
        for (int i = 0; i < 9; i++) {
            if (playerCount[i]) {
                d_index[d_counter] = i;
                d_counter++;
            }
        }
        //check winning possibilities for all combination of 2 nos i.e 3C2
        int[] d_results = new int[3];
        d_results[0] = check(d_index[0], d_index[1]);
        d_results[1] = check(d_index[1], d_index[2]);
        d_results[2] = check(d_index[0], d_index[2]);


        if (d_results[0] != -1) {
            if (!mainCount[d_results[0]])
                return d_results[0];
        }
        if (d_results[1] != -1) {
            if (!mainCount[d_results[1]])
                return d_results[1];
        }
        if (d_results[2] != -1) {
            if (!mainCount[d_results[2]])
                return d_results[2];
        }



       /* for (int k = 0; k < 9; k++) {
            if (!mainCount[k]) return k;

        }*/

        // if both defence and attack fails then ret random

        Random random = new Random();
        ArrayList<Integer> not_filled = new ArrayList();
        for (int k = 0; k < 9; k++) {
            if (!mainCount[k]) {
                not_filled.add(k);
            }
        }
        int rand_index = random.nextInt(not_filled.size());
        return not_filled.get(rand_index);
    }


    private int checkFour() {


        // attack algorithm for 3 comp animals
        Log.d("DARSHANROHAN", "" + OptionsActivity.AdultModeOn);
        if (OptionsActivity.AdultModeOn) {
            int[] a_index = new int[3];
            int a_counter = 0;
            //get all three positions of comp's animals
            for (int i = 0; i < 9; i++) {
                if (compCount[i]) {
                    a_index[a_counter] = i;
                    a_counter++;
                }
            }
            //check winning possibilities for all combination of 2 nos i.e 3C2
            int[] a_results = new int[3];
            a_results[0] = check(a_index[0], a_index[1]);
            a_results[1] = check(a_index[1], a_index[2]);
            a_results[2] = check(a_index[0], a_index[2]);


            if (a_results[0] != -1) {
                if (!mainCount[a_results[0]])
                    return a_results[0];
            }
            if (a_results[1] != -1) {
                if (!mainCount[a_results[1]])
                    return a_results[1];
            }
            if (a_results[2] != -1) {
                if (!mainCount[a_results[2]])
                    return a_results[2];
            }
        }

        //defence if can't win by attack

        int[] index = new int[4];
        int counter = 0;
        //get all three positions of player's animals
        for (int i = 0; i < 9; i++) {
            if (playerCount[i]) {
                index[counter] = i;
                counter++;
            }
        }

        //check winning possibilities for all combination of 2 nos i.e 4C2

        int[] results = new int[6];
        results[0] = check(index[0], index[1]);
        results[1] = check(index[0], index[2]);
        results[2] = check(index[0], index[3]);
        results[3] = check(index[1], index[2]);
        results[4] = check(index[1], index[3]);
        results[5] = check(index[2], index[3]);


        if (results[0] != -1) {
            if (!mainCount[results[0]])
                return results[0];
        }
        if (results[1] != -1) {
            if (!mainCount[results[1]])
                return results[1];
        }
        if (results[2] != -1) {
            if (!mainCount[results[2]])
                return results[2];
        }
        if (results[3] != -1) {
            if (!mainCount[results[3]])
                return results[3];
        }
        if (results[4] != -1) {
            if (!mainCount[results[4]])
                return results[4];
        }
        if (results[5] != -1) {
            if (!mainCount[results[5]])
                return results[5];
        }

       /* for (int k = 0; k < 9; k++) {
            if (!mainCount[k]) return k;

        }*/

        // if cant defend o attack then random box

        Random random = new Random();
        ArrayList<Integer> not_filled = new ArrayList();
        for (int k = 0; k < 9; k++) {
            if (!mainCount[k]) {
                not_filled.add(k);
            }
        }
        int rand_index = random.nextInt(not_filled.size());
        return  not_filled.get(rand_index);
        // return 0;
    }

    private int check(int fi, int sn) {
        int si = -1;

        for (int i = 0; i < b[fi].length; i++) {
            for (int j = 0; j < 2; j++) {
                if (b[fi][i][j] == sn) {
                    if (j == 0) return b[fi][i][1];
                    else return b[fi][i][0];
                }
            }
        }
        return si;
    }

    private int checkForAlreadyClicked(int playOn) {
        if (mainCount[playOn]) {
            for (int k = 0; k < 9; k++) {
                if (!mainCount[k]) return k;
            }
        } else return playOn;

        return -1;
    }

    private void compClicked(int playOn) {
        animationRunning = true;
        mainCount[playOn] = true;
        compCount[playOn] = true;
        playCustomAnimationForComp(playOn);
    }

    private void playCustomAnimationForComp(final int playOn) {
        YoYo.with(main[playOn]).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivMain.setBackgroundResource(compImage);
                ivMain.setVisibility(View.VISIBLE);
                playSound(compSound);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv[playOn].setBackgroundResource(compImage);
                YoYo.with(sub[playOn]).duration(500).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationRunning = false;
                        checkForGame();
                        changeColor(1);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(iv[playOn]);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(1500).playOn(ivMain);
    }

    private void callCompPlay() {
        int playOn;
        switch (timesClicked) {
            case 1:
                playOn = checkOne();
                compClicked(playOn);
                break;
            case 2:
                playOn = checkTwo();
                playOn = checkForAlreadyClicked(playOn);
                compClicked(playOn);
                break;
            case 3:
                playOn = checkThree();
//                playOn = checkForAlreadyClicked(playOn);
                compClicked(playOn);
                break;
            case 4:
                playOn = checkFour();
                playOn = checkForAlreadyClicked(playOn);
                compClicked(playOn);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences mSharedPreferences = getSharedPreferences(FirstScreen.POINTS_PREFS, MODE_APPEND);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(FirstScreen.POINTS_PLAYED, OnePlayerStatisticsFragment.played);
        editor.putInt(FirstScreen.POINTS_WON, OnePlayerStatisticsFragment.won);
        editor.putInt(FirstScreen.POINTS_LOST, OnePlayerStatisticsFragment.lost);
        editor.putInt(FirstScreen.POINTS_DRAW, OnePlayerStatisticsFragment.draw);
        float won, played, draw;
        won = OnePlayerStatisticsFragment.won;
        played = OnePlayerStatisticsFragment.played;
        draw = OnePlayerStatisticsFragment.draw;
        OnePlayerStatisticsFragment.winPercent = (won / (played - draw)) * 100;
        Log.d("winPercent", "" + OnePlayerStatisticsFragment.winPercent);
        editor.putFloat(FirstScreen.WIN_PERCENT, OnePlayerStatisticsFragment.winPercent);
        editor.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("seperate", "onSaveInstance called");
        super.onSaveInstanceState(outState);
        outState.putBooleanArray("c", mainCount);
        outState.putBooleanArray("cG", playerCount);
        outState.putBooleanArray("cR", compCount);
        outState.putBoolean("switchC", switchC);
        outState.putInt("player1image", playerImage);
        outState.putInt("player2image", compImage);
        outState.putInt("player1sound", playerSound);
        outState.putInt("player2sound", compSound);
        outState.putString("player_1_name", playerName);
        outState.putString("player_2_name", compName);
        outState.putInt("player1points", player1points);
        outState.putInt("player2points", player2points);
        outState.putBoolean("animationRunning", animationRunning);

    }

    private void restore(Bundle savedInstanceState) {
        Log.d("seperate", "restore called");
        switchC = savedInstanceState.getBoolean("switchC");
        mainCount = savedInstanceState.getBooleanArray("c");
        playerCount = savedInstanceState.getBooleanArray("cG");
        compCount = savedInstanceState.getBooleanArray("cR");
        playerImage = savedInstanceState.getInt("player1image", -1);
        compImage = savedInstanceState.getInt("player2image", -1);
        playerSound = savedInstanceState.getInt("player1sound", -1);
        compSound = savedInstanceState.getInt("player2sound", -1);
        animationRunning = savedInstanceState.getBoolean("animationRunning", false);
        compName = savedInstanceState.getString("player_2_name");
        playerName = savedInstanceState.getString("player_1_name");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        for (int d = 0; d < 9; d++) {
            if (playerCount[d]) {
                iv[d].setBackgroundResource(playerImage);
            }
        }
        for (int d = 0; d < 9; d++) {
            if (compCount[d]) {
                iv[d].setBackgroundResource(compImage);
            }
        }
    }
}
