package com.twinvaves.darshan.darshantestproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingData extends AsyncTask<Context, Void, Boolean> {
    int points1, points2;
    String player1, player2;
    FileOutputStream fos;
    Context mContext;
    public static final String FILE_NAME = "TwoPlayerPoints.txt";

    public void setPoints1(int points1) {
        this.points1 = points1;
    }

    public void setPoints2(int points2) {
        this.points2 = points2;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    @Override
    protected Boolean doInBackground(Context... params) {
        boolean successful = false;
        mContext = params[0];
        try {
            fos = params[0].openFileOutput(FILE_NAME, Context.MODE_APPEND);
            String store = "" + player1 + "-" + points1 + "&" + player2 + "-" + points2 + "$";
            fos.write(store.getBytes());
            successful = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return successful;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (aBoolean)
            Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Failed to save", Toast.LENGTH_SHORT).show();
    }
}
