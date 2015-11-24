package com.twinvaves.darshan.darshantestproject;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Ramesh on 3/17/2015.
 */
public class mainApplication extends Application {

    public static String PLAYERNAME = null, COMPUTERNAME = null;

    public Drawable resize(int image) {
        Drawable img = getResources().getDrawable(image);
        Bitmap b = ((BitmapDrawable) img).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 50, 50, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

}
