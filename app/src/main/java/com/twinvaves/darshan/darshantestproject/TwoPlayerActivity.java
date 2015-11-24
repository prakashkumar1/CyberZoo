package com.twinvaves.darshan.darshantestproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.ButtonRectangle;
import com.nineoldandroids.animation.Animator;


public class TwoPlayerActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView ivMain;
    ImageView iv[] = new ImageView[9];
    boolean[] c = {false, false, false, false, false, false, false, false, false};
    boolean[] cR = {false, false, false, false, false, false, false, false, false};
    boolean[] cG = {false, false, false, false, false, false, false, false, false};
    boolean switchC = false;
    int player_1_image, player_2_image;
    int player_1_sound, player_2_sound;
    static int winPlayer = 0;
    private ButtonRectangle player1, player2;
    private RippleView mainView;
    private TextView points1, points2;
    String player_1_name, player_2_name;
    public String ActualPlayerOneName, ActualPlayerTwoName;
    private int player1points, player2points;
    private boolean animationRunning = false;
    boolean wonTheMatch = false;
    static int player1clicked = 0, player2clicked = 0;
    MediaPlayer mp = null;
    boolean compCombination1, compCombination2, compCombination3, compCombination4, compCombination5, compCombination6, compCombination7, compCombination8;
    boolean playerCombination1, playerCombination2, playerCombination3, playerCombination4, playerCombination5, playerCombination6, playerCombination7, playerCombination8;
    Intent intent;

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
        intent = getIntent();
        player_1_image = intent.getIntExtra(Categories.PLAYER_1, -1);
        player_2_image = intent.getIntExtra(Categories.PLAYER_2, -1);
        player_1_name = intent.getStringExtra(Categories.PLAYER_1_NAME);
        player_2_name = intent.getStringExtra(Categories.PLAYER_2_NAME);
        ActualPlayerOneName = intent.getStringExtra(Categories.ACTUAL_NAME_1);
        ActualPlayerTwoName = intent.getStringExtra(Categories.ACTUAL_NAME_2);
        player_1_sound = intent.getIntExtra(Categories.PLAYER_1_SOUND, -1);
        player_2_sound = intent.getIntExtra(Categories.PLAYER_2_SOUND, -1);
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
        player1.setText("" + player_1_name);
        player2.setText("" + player_2_name);
        player1.setRippleSpeed(50);
        player2.setRippleSpeed(50);
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1clicked == 0) {
                    player1clicked = 1;
                    if (mainApplication.PLAYERNAME.equals(null) || mainApplication.PLAYERNAME.equals("")) {
                        player1.setText("player");
                    } else {
                        Log.d("RAJ", "comp name " + mainApplication.PLAYERNAME);
                        player1.setText("" + mainApplication.PLAYERNAME);
                    }
                } else if (player1clicked == 1) {
                    player1clicked = 0;
                    player1.setText("" + player_1_name);
                }

            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (player2clicked == 0) {
                    player2clicked = 1;
                    if (mainApplication.COMPUTERNAME.equals(null) || mainApplication.COMPUTERNAME.equals("")) {
                        player2.setText("mobile");
                    } else {
                        Log.d("RAJ", "comp name " + mainApplication.COMPUTERNAME);
                        player2.setText("" + mainApplication.COMPUTERNAME);
                    }
                } else if (player2clicked == 1) {
                    player2clicked = 0;
                    player2.setText("" + player_2_name);
                }*/
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.twinvave.godiswithme"));
                startActivity(intent1);
            }
        });
        points1 = (TextView) findViewById(R.id.tvPoints1);
        points2 = (TextView) findViewById(R.id.tvPoints2);
        points1.setText("" + player1points);
        points2.setText("" + player2points);


    }


    @Override
    public void onClick(View v) {
        int which;
        if (animationRunning == false) {
            Log.d("onclick", "onClick called");
            switch (v.getId()) {
                case R.id.iv1:
                    which = onclick(0);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutLeftUp, Techniques.FadeInDown, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv2:
                    which = onclick(1);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutUp, Techniques.FadeInDown, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv3:
                    which = onclick(2);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutRightUp, Techniques.FadeInDown, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv4:
                    which = onclick(3);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutLeft, Techniques.FadeInLeft, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv5:
                    which = onclick(4);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOut, Techniques.FadeIn, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv6:
                    which = onclick(5);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutRight, Techniques.FadeInRight, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv7:
                    which = onclick(6);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutLeftDown, Techniques.FadeInUp, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv8:
                    which = onclick(7);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutDown, Techniques.FadeInUp, v, which);
                        playSound(which);
                    }
                    break;
                case R.id.iv9:
                    which = onclick(8);
                    if (which != -1) {
                        playAnimation(Techniques.ZoomOutRightDown, Techniques.FadeInUp, v, which);
                        playSound(which);
                    }
                    break;
            }
        }

    }

    private void playAnimation(final Techniques techMain, final Techniques techSub, final View v, final int image) {
        YoYo.with(techMain).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationRunning = true;
                if (image == 0) {
                    ivMain.setBackgroundResource(player_1_image);
                } else if (image == 1) {
                    ivMain.setBackgroundResource(player_2_image);
                }
                ivMain.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (image == 0) {
                    v.setBackgroundResource(player_1_image);
                } else if (image == 1) {
                    v.setBackgroundResource(player_2_image);
                }
                YoYo.with(techSub).duration(500).playOn(v);
                animationRunning = false;
                checkForGame();

                changeColor(image);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(1500).playOn(ivMain);
    }

    private void checkForGame() {
        if (OptionsActivity.SoundOn) {
            if (mp != null) {
                mp.reset();
                Log.d("mediaplayer", "reset");
            }
        }
        compCombination1 = (cG[0] && cG[1] && cG[2]);
        compCombination2 = (cG[3] && cG[4] && cG[5]);
        compCombination3 = (cG[6] && cG[7] && cG[8]);
        compCombination4 = (cG[0] && cG[3] && cG[6]);
        compCombination5 = (cG[1] && cG[4] && cG[7]);
        compCombination6 = (cG[2] && cG[5] && cG[8]);
        compCombination7 = (cG[0] && cG[4] && cG[8]);
        compCombination8 = (cG[2] && cG[4] && cG[6]);
        if (compCombination1 || compCombination2 || compCombination3
                || compCombination4 || compCombination5 || compCombination6
                || compCombination7 || compCombination8) {
            winPlayer = 1;
            if (wonTheMatch == false) wonTheMatch = true;
            else wonTheMatch = false;
        }

        playerCombination1 = (cR[0] && cR[1] && cR[2]);
        playerCombination2 = (cR[3] && cR[4] && cR[5]);
        playerCombination3 = (cR[6] && cR[7] && cR[8]);
        playerCombination4 = (cR[0] && cR[3] && cR[6]);
        playerCombination5 = (cR[1] && cR[4] && cR[7]);
        playerCombination6 = (cR[2] && cR[5] && cR[8]);
        playerCombination7 = (cR[0] && cR[4] && cR[8]);
        playerCombination8 = (cR[2] && cR[4] && cR[6]);
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
        } else if (winPlayer == 2) {
            int[] who = checkWhichPlayerComboWon();
            playWinAnimation(who, 1);
//            WinDialog(1);
        } else if (c[0] && c[1] && c[2] && c[3] && c[4] && c[5] && c[6] && c[7] && c[8]) {
            if (wonTheMatch == false) wonTheMatch = true;
            else wonTheMatch = false;
            playDrawAnimation(-1);

//            WinDialog(-1);

        }
    }

    private void WinDialog(final int i) {
        animationRunning = true;
        String title = null;
        Log.d("", "" + wonTheMatch);
        if (wonTheMatch) {
            if (i != -1) {
                if (i == 1) {
                    title = "" + player_1_name + " won!";
                } else if (i == 2) {
                    title = "" + player_2_name + " won!";
                }
            } else title = "It's a tie!!";

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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
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
                            if (i == 1)
                                player1points++;
                            else if
                                    (i == 2) player2points++;
                            reset();
                            callCustomBackPressed();
//                            TwoPlayerActivity.this.finish();
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
                            Intent in = new Intent(TwoPlayerActivity.this, Categories.class);
                            in.putExtra("getWhich", 2);
                            in.putExtra(Categories.PLAYER_1_POINTS, player1points);
                            in.putExtra(Categories.PLAYER_2_POINTS, player2points);
                            in.putExtra(Categories.PLAYER_1_NAME, ActualPlayerOneName);
                            in.putExtra(Categories.PLAYER_2_NAME, ActualPlayerTwoName);
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


    private int onclick(int i) {
        animationRunning = true;
        if (!c[i]) {
            c[i] = true;

            if (!switchC) {
                switchC = true;
                cR[i] = true;

                return 0;
            } else {
                switchC = false;
                cG[i] = true;

                return 1;
            }
        } else {
            Toast.makeText(this, "Already clicked", Toast.LENGTH_SHORT).show();
            animationRunning = false;
        }
        return -1;
    }

    private void playSound(int sound) {
        if (OptionsActivity.SoundOn) {
            if (sound == 0) {
                mp = MediaPlayer.create(getApplicationContext(), player_1_sound);
            } else if (sound == 1) {
                mp = MediaPlayer.create(getApplicationContext(), player_2_sound);
            }
            try {
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
        wonTheMatch = false;
        changeColor(1);
        animationRunning = false;
        ivMain.setBackgroundResource(0);
        for (int a = 0; a < 9; a++) {
            c[a] = false;
            cR[a] = false;
            cG[a] = false;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray("c", c);
        outState.putBooleanArray("cG", cG);
        outState.putBooleanArray("cR", cR);
        outState.putBoolean("switchC", switchC);
        outState.putInt("player1image", player_1_image);
        outState.putInt("player2image", player_1_image);
        outState.putInt("player1sound", player_1_sound);
        outState.putInt("player2sound", player_2_sound);
        outState.putString("player_1_name", player_1_name);
        outState.putString("player_2_name", player_2_name);
        outState.putInt("player1points", player1points);
        outState.putInt("player2points", player2points);
        outState.putBoolean("animationRunning", animationRunning);

    }

    private void restore(Bundle savedInstanceState) {

        switchC = savedInstanceState.getBoolean("switchC");
        c = savedInstanceState.getBooleanArray("c");
        cG = savedInstanceState.getBooleanArray("cG");
        cR = savedInstanceState.getBooleanArray("cR");
        player_1_image = savedInstanceState.getInt("player1image", -1);
        player_2_image = savedInstanceState.getInt("player2image", -1);
        player_1_sound = savedInstanceState.getInt("player1sound", -1);
        player_2_sound = savedInstanceState.getInt("player2sound", -1);
        animationRunning = savedInstanceState.getBoolean("animationRunning", false);
        player_2_name = savedInstanceState.getString("player_2_name");
        player_1_name = savedInstanceState.getString("player_1_name");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        for (int d = 0; d < 9; d++) {
            if (cR[d]) {
                iv[d].setBackgroundResource(player_1_image);
            }
        }
        for (int d = 0; d < 9; d++) {
            if (cG[d]) {
                iv[d].setBackgroundResource(player_2_image);
            }
        }
    }

    private void playDrawAnimation(final int i) {
        animationRunning = true;
        for (int jo = 0; jo < 8; jo++) {
            YoYo.with(Techniques.Shake).duration(1000).playOn(iv[jo]);
        }
        YoYo.with(Techniques.Shake).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationRunning = true;

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
                animationRunning = true;

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

    @Override
    public void onBackPressed() {
        callCustomBackPressed();

    }

    private void callCustomBackPressed() {
        if ((player2points == 0) && (player1points == 0)) {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this).setTitle("Scores not saved in your name!").setMessage("Save the points?").setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callDialog();

                }
            }).setNegativeButton("Don't save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TwoPlayerActivity.super.onBackPressed();
                }
            }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setCancelable(false).show();
        }

    }

    private void callDialog() {
        final View v = getLayoutInflater().inflate(R.layout.select_dialog, null);
        final ImageView iv1, iv2;
        final EditText tvPlayer1, tvPlayer2;
        tvPlayer1 = (EditText) v.findViewById(R.id.tvName1);
        tvPlayer2 = (EditText) v.findViewById(R.id.tvName2);
        iv1 = (ImageView) v.findViewById(R.id.iv1);
        iv2 = (ImageView) v.findViewById(R.id.iv2);
        iv1.setBackgroundResource(player_1_image);
        iv2.setBackgroundResource(player_2_image);
        tvPlayer1.setText(ActualPlayerOneName);
        tvPlayer2.setText(ActualPlayerTwoName);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Save with the name?").setView(v).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
                mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();
                if (mainApplication.PLAYERNAME.equals("")) {
                    mainApplication.PLAYERNAME = "Player1";
                }
                if (mainApplication.COMPUTERNAME.equals("")) {
                    mainApplication.COMPUTERNAME = "Player2";
                }
                WritingData writingData = new WritingData();
                writingData.setPlayer1(mainApplication.PLAYERNAME);
                writingData.setPlayer2(mainApplication.COMPUTERNAME);
                writingData.setPoints1(player1points);
                writingData.setPoints2(player2points);
                writingData.execute(getApplicationContext());
                TwoPlayerActivity.this.finish();
            }
        }).show();
    }
}
