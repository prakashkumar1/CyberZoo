package com.twinvaves.darshan.darshantestproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoPlayerStatisticsFragment extends Fragment {

    public static ArrayList<View> views = new ArrayList<View>();
    ListView mListView;
    public ReadData.dataClass mDataClass;

    public TwoPlayerStatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("seperate", "view length" + views.size());
        View mView =
                inflater.inflate(R.layout.fragment_two_player_statistics, container, false);
        mListView = (ListView) mView.findViewById(R.id.pointsListView);
        mListView.setAdapter(new CustomAdapter());
        return mView;
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ReadData.playersData.size();
        }

        @Override
        public Object getItem(int position) {
            mDataClass = ReadData.playersData.get(position);
            return mDataClass;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class viewHolder {
            View pointsView;
            TextView tv1, tv2;
            ButtonRectangle name1, name2;

            viewHolder(View v) {
                pointsView = v;
                tv1 = (TextView) pointsView.findViewById(R.id.tvPoints1);
                tv2 = (TextView) pointsView.findViewById(R.id.tvPoints2);
                name1 = (ButtonRectangle) pointsView.findViewById(R.id.NamePlayer1);
                name2 = (ButtonRectangle) pointsView.findViewById(R.id.NamePlayer2);
            }


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder mViewHolder = null;
            mDataClass = ReadData.playersData.get(position);
            if (convertView == null) {
                LayoutInflater mInflater = getLayoutInflater(null);
                convertView = mInflater.inflate(R.layout.single_points_view, null);
                mViewHolder = new viewHolder(convertView);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (viewHolder) convertView.getTag();
            }
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rosemary.ttf");
            mViewHolder.tv1.setTypeface(tf);
            mViewHolder.tv2.setTypeface(tf);
            mViewHolder.name1.setTypeFace(tf);
            mViewHolder.name2.setTypeFace(tf);
            mViewHolder.tv1.setText("" + mDataClass.points1);
            mViewHolder.tv2.setText("" + mDataClass.points2);
            mViewHolder.name1.setText("" + mDataClass.player1name);
            mViewHolder.name2.setText("" + mDataClass.player2name);

            return convertView;
        }
    }

}
