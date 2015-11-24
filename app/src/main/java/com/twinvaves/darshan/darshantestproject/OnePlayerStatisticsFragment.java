package com.twinvaves.darshan.darshantestproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnePlayerStatisticsFragment extends Fragment {

    TextView gamesPlayed, gamesWon, gamesLost, gamesDraw, winPercentage;
    ButtonRectangle gP, gW, gL, gD, wP;
    public static int played, won, lost, draw;
    public static float winPercent;

    public OnePlayerStatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =
                inflater.inflate(R.layout.fragment_one_player_statistics, container, false);
        init(mView);
        return mView;
    }

    private void init(View mView) {
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rosemary.ttf");
        gamesPlayed = (TextView) mView.findViewById(R.id.tvGamesPlayed);
        gamesWon = (TextView) mView.findViewById(R.id.tvGamesWon);
        gamesLost = (TextView) mView.findViewById(R.id.tvGamesLost);
        gamesDraw = (TextView) mView.findViewById(R.id.tvGamesDraw);
        winPercentage = (TextView) mView.findViewById(R.id.tvWinPercentage);
        gP = (ButtonRectangle) mView.findViewById(R.id.GamesPlayed);
        gW = (ButtonRectangle) mView.findViewById(R.id.GamesWon);
        gL = (ButtonRectangle) mView.findViewById(R.id.GamesLost);
        gD = (ButtonRectangle) mView.findViewById(R.id.GamesDraw);
        wP = (ButtonRectangle) mView.findViewById(R.id.WinPercentage);

        gamesPlayed.setTypeface(tf);
        gamesWon.setTypeface(tf);
        gamesDraw.setTypeface(tf);
        gamesLost.setTypeface(tf);
        winPercentage.setTypeface(tf);
        gP.setTypeFace(tf);
        gW.setTypeFace(tf);
        gL.setTypeFace(tf);
        gD.setTypeFace(tf);
        wP.setTypeFace(tf);

        gamesPlayed.setText("" + played);
        gamesWon.setText("" + won);
        gamesLost.setText("" + lost);
        gamesDraw.setText("" + draw);
        winPercentage.setText("" + (int) winPercent);
    }

}
