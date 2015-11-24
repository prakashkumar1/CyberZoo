package com.twinvaves.darshan.darshantestproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ReadData extends AsyncTask<Context, Void, Boolean> {
    public static String allPoints = "";
    Context context;
    LayoutInflater mLayoutInflater;
    StringBuffer fileContent = new StringBuffer("");
    ArrayList<String> players = new ArrayList<>();

    public static ArrayList<dataClass> playersData = new ArrayList<>();

    public ReadData(Context context, LayoutInflater mLayoutInflater) {
        this.context = context;
        this.mLayoutInflater = mLayoutInflater;
    }

    Boolean success = false;

    public void readPoints() {
        if (fileExistance(WritingData.FILE_NAME)) {
            try {
                FileInputStream fis = context.openFileInput(WritingData.FILE_NAME);
                int length;
                while ((length = fis.read()) != -1) {
                    fileContent.append((char) length);
                }
                success = true;
                fis.close();
                allPoints = fileContent.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            success = false;


    }

    private void seperatePoints() {
        String obtainedString = allPoints;
        int start;
        int end;
        for (int i = 0; i < obtainedString.length(); i++) {
            if (obtainedString.contains("$")) {
                start = 0;
                end = obtainedString.indexOf('$');
                players.add(obtainedString.substring(start, end));
                obtainedString = obtainedString.substring(end + 1);
            }
        }
    }


    private void seperatePlayers() {
        for (int i = 0; i < players.size(); i++) {
            dataClass mDataClass = new dataClass();
            String tempPlayer = players.get(i);
            mDataClass.player1name = tempPlayer.substring(0, tempPlayer.indexOf('-'));
            tempPlayer = tempPlayer.substring(tempPlayer.indexOf('-') + 1);
            mDataClass.points1 = tempPlayer.substring(0, tempPlayer.indexOf('&'));
            tempPlayer = tempPlayer.substring(tempPlayer.indexOf('&') + 1);
            mDataClass.player2name = tempPlayer.substring(0, tempPlayer.indexOf('-'));
            tempPlayer = tempPlayer.substring(tempPlayer.indexOf('-') + 1);
            mDataClass.points2 = tempPlayer.substring(0);
            mDataClass.total = Integer.parseInt(mDataClass.points1) + Integer.parseInt(mDataClass.points2);
            if (!checkDupliactes(mDataClass.player1name, mDataClass.player2name, mDataClass.points1, mDataClass.points2)) {
                playersData.add(mDataClass);
            }


        }
        Comparator<dataClass> comparator = new Comparator<dataClass>() {
            @Override
            public int compare(dataClass o1, dataClass o2) {
                if (o1.total == o2.total)
                    return 0;
                return o1.total < o2.total ? -1 : 1;
            }
        };

        Collections.sort(playersData, Collections.reverseOrder(comparator));


    }

    private boolean checkDupliactes(String player1name, String player2name, String points1, String points2) {
        dataClass mDataClass;
//        dataClass nDataClass = new dataClass();
        for (int i = 0; i < playersData.size(); i++) {
            mDataClass = playersData.get(i);
            Log.d("raju", "" + mDataClass);
            if (mDataClass.player1name.equals(player1name) && mDataClass.player2name.equals(player2name) &&
                    mDataClass.points1.equals(points1) && mDataClass.points2.equals(points2)) {
//                int p1 = Integer.parseInt(mDataClass.points1) + Integer.parseInt(points1);
//                int p2 = Integer.parseInt(mDataClass.points2) + Integer.parseInt(points2);
//                Log.d("raju", "p1 -" + p1 + " p2-" + p2);
//
//                nDataClass.player1name = player1name;
//                nDataClass.player2name = player2name;
//                nDataClass.points1 = "" + p1;
//                nDataClass.points2 = "" + p2;
//                Log.d("raju", "" + nDataClass);
//
//                playersData.remove(i);
//                playersData.add(i, nDataClass);
                return true;
            }
        }
        return false;
    }

    @Override
    protected Boolean doInBackground(Context... params) {
        readPoints();
        seperatePoints();
        seperatePlayers();
        return success;
    }


    public boolean fileExistance(String fname) {
        File file = context.getFileStreamPath(fname);
        return file.exists();
    }

    public class dataClass {
        String player1name, player2name;
        String points1, points2;
        int total;
    }
}
