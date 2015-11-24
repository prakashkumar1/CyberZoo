package com.twinvaves.darshan.darshantestproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundViewFragment extends Fragment {

    public ImageView imageView;

    public BackgroundViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_background_view, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageBackground);
        return view;
    }


}
