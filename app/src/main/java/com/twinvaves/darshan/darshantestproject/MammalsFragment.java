package com.twinvaves.darshan.darshantestproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MammalsFragment extends Fragment{

    RecyclerView mListView;
    int[] mammals1 = {R.drawable.dog, R.drawable.elephant, R.drawable.deer, R.drawable.rabbit};
    int[] mammals2 = {R.drawable.cat, R.drawable.chimpanzee, R.drawable.kangaroo, R.drawable.rat};
    String[] mammalNames1 = {"Dog", "Elephant", "Deer", "Rabbit", "Ant"};
    String[] mammalNames2 = {"Cat", "Chimpanzee", "Kangaroo", "Rat", "Cockroach"};
    int[] sound1 = {R.raw.dog, R.raw.elephant, R.raw.deer, R.raw.rabbit};
    int[] sound2 = {R.raw.cat, R.raw.chimpanzee, R.raw.kangaroo, R.raw.rat};
    boolean clicked = false;
    int player1, player2;
    int player1sound, player2sound;
    String name1, name2;
    String ActualName1, ActualName2;

    public MammalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.fragment_mammals, container, false);
       /* mListView = (ListView) mView.findViewById(R.id.mammalsListView);
        mListView.setAdapter(new MammalsListViewAdapter());
        mListView.setOnItemClickListener(this);*/

        mListView = (RecyclerView) mView.findViewById(R.id.mammalsListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(new MyMammalsAdapter());
        return mView;
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//        final View v = getLayoutInflater(null).inflate(R.layout.select_dialog, null);
//        final ImageView iv1, iv2;
//        final EditText tvPlayer1, tvPlayer2;
//        tvPlayer1 = (EditText) v.findViewById(R.id.tvName1);
//        tvPlayer2 = (EditText) v.findViewById(R.id.tvName2);
//        if (Categories.which == 1) {
//            if (mainApplication.COMPUTERNAME == null || mainApplication.PLAYERNAME == null) {
//                tvPlayer1.setHint("Player");
//                tvPlayer2.setHint("Mobile");
//            } else {
//                tvPlayer1.setText(mainApplication.PLAYERNAME);
//                tvPlayer2.setText(mainApplication.COMPUTERNAME);
//            }
//
//        } else if (Categories.which == 2) {
//            if (mainApplication.COMPUTERNAME == null || mainApplication.PLAYERNAME == null) {
//                tvPlayer1.setHint("Player 1");
//                tvPlayer2.setHint("Player 2");
//            } else {
//                tvPlayer1.setText(mainApplication.PLAYERNAME);
//                tvPlayer2.setText(mainApplication.COMPUTERNAME);
//            }
//
//        }
//        iv1 = (ImageView) v.findViewById(R.id.iv1);
//        iv2 = (ImageView) v.findViewById(R.id.iv2);
//        iv1.setBackgroundResource(mammals1[position]);
//        iv2.setBackgroundResource(mammals2[position]);
//        name1 = mammalNames1[position];
//        name2 = mammalNames2[position];
//        player1sound = sound1[position];
//        player2sound = sound2[position];
//        player1 = mammals1[position];
//        player2 = mammals2[position];
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(v)
//                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
//                        mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();
//                        /*if (ActualName1.equals("") || ActualName1.equals(null)) {
//                            ActualName1 = Categories.p1Name;
//                        }
//                        if (ActualName2.equals("") || ActualName2.equals(null)) {
//                            ActualName2 = Categories.p2Name;
//                        }*/
//                        if (Categories.which == 1) {
//                            Intent in = new Intent(getActivity(), OnePlayerActivity.class);
//                            in.putExtra(Categories.PLAYER_1, player1);
//                            in.putExtra(Categories.PLAYER_2, player2);
//                            in.putExtra(Categories.PLAYER_1_NAME, name1);
//                            in.putExtra(Categories.PLAYER_2_NAME, name2);
//                            in.putExtra(Categories.PLAYER_1_SOUND, player1sound);
//                            in.putExtra(Categories.PLAYER_2_SOUND, player2sound);
//                            in.putExtra(Categories.ACTUAL_NAME_1, mainApplication.PLAYERNAME);
//                            in.putExtra(Categories.ACTUAL_NAME_2, mainApplication.COMPUTERNAME);
//                            startActivity(in);
//                            getActivity().finish();
//                        }
//                        if (Categories.which == 2) {
//                            Intent in = new Intent(getActivity(), TwoPlayerActivity.class);
//                            in.putExtra(Categories.PLAYER_1, player1);
//                            in.putExtra(Categories.PLAYER_2, player2);
//                            in.putExtra(Categories.PLAYER_1_NAME, name1);
//                            in.putExtra(Categories.PLAYER_2_NAME, name2);
//                            in.putExtra(Categories.PLAYER_1_SOUND, player1sound);
//                            in.putExtra(Categories.PLAYER_2_SOUND, player2sound);
//                            in.putExtra(Categories.ACTUAL_NAME_1, mainApplication.PLAYERNAME);
//                            in.putExtra(Categories.ACTUAL_NAME_2, mainApplication.COMPUTERNAME);
//                            startActivity(in);
//                            getActivity().finish();
//                        }
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        clicked = false;
//                        dialog.dismiss();
//                    }
//                })
//                .setNeutralButton("Switch", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!clicked) {
//
//                    iv1.setBackgroundResource(mammals2[position]);
//                    iv2.setBackgroundResource(mammals1[position]);
//                    player1 = mammals2[position];
//                    player2 = mammals1[position];
//                    name1 = mammalNames2[position];
//                    name2 = mammalNames1[position];
//                    player1sound = sound2[position];
//                    player2sound = sound1[position];
//                    clicked = true;
//                } else {
//
//                    iv1.setBackgroundResource(mammals1[position]);
//                    iv2.setBackgroundResource(mammals2[position]);
//                    player1 = mammals1[position];
//                    player2 = mammals2[position];
//                    name1 = mammalNames1[position];
//                    name2 = mammalNames2[position];
//                    player1sound = sound1[position];
//                    player2sound = sound2[position];
//                    clicked = false;
//                }
//            }
//        });
//    }


//    public class MammalsListViewAdapter extends BaseAdapter {
//
//
//        @Override
//        public int getCount() {
//            return mammals1.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mammals1[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//
//        class Holder {
//            ImageView mImageView1, mImageView2;
//            TextView mTextView;
//
//            Holder(View v) {
//                mImageView1 = (ImageView) v.findViewById(R.id.imageView);
//                mImageView2 = (ImageView) v.findViewById(R.id.imageView2);
//                mTextView = (TextView) v.findViewById(R.id.textView);
//            }
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Holder mHolder = null;
//            if (convertView == null) {
//                LayoutInflater mLayoutInflater = getLayoutInflater(null);
//                convertView = mLayoutInflater.inflate(R.layout.single_view, null);
//                mHolder = new Holder(convertView);
//                convertView.setTag(mHolder);
//            } else {
//                mHolder = (Holder) convertView.getTag();
//            }
//            mHolder.mImageView1.setBackgroundResource(mammals1[position]);
//            mHolder.mImageView2.setBackgroundResource(mammals2[position]);
//            mHolder.mTextView.setText("" + mammalNames1[position] + " & " + mammalNames2[position]);
//
//            return convertView;
//        }
//    }

    public class MyMammalsAdapter extends RecyclerView.Adapter<MyMammalsAdapter.MammalsHolder> {
        @Override
        public MammalsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater(null).inflate(R.layout.single_view, null);
            MammalsHolder mammalsHolder = new MammalsHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = mListView.getChildPosition(view);
                    final View v = getLayoutInflater(null).inflate(R.layout.select_dialog, null);
                    final ImageView iv1, iv2;
                    final EditText tvPlayer1, tvPlayer2;
                    tvPlayer1 = (EditText) v.findViewById(R.id.tvName1);
                    tvPlayer2 = (EditText) v.findViewById(R.id.tvName2);
                    if (Categories.which == 1) {
                        if (mainApplication.COMPUTERNAME == null || mainApplication.PLAYERNAME == null) {
                            tvPlayer1.setHint("Player");
                            tvPlayer2.setHint("Mobile");
                        } else {
                            tvPlayer1.setText(mainApplication.PLAYERNAME);
                            tvPlayer2.setText(mainApplication.COMPUTERNAME);
                        }

                    } else if (Categories.which == 2) {
                        if (mainApplication.COMPUTERNAME == null || mainApplication.PLAYERNAME == null) {
                            tvPlayer1.setHint("Player 1");
                            tvPlayer2.setHint("Player 2");
                        } else {
                            tvPlayer1.setText(mainApplication.PLAYERNAME);
                            tvPlayer2.setText(mainApplication.COMPUTERNAME);
                        }

                    }
                    iv1 = (ImageView) v.findViewById(R.id.iv1);
                    iv2 = (ImageView) v.findViewById(R.id.iv2);
                    iv1.setBackgroundResource(mammals1[position]);
                    iv2.setBackgroundResource(mammals2[position]);
                    name1 = mammalNames1[position];
                    name2 = mammalNames2[position];
                    player1sound = sound1[position];
                    player2sound = sound2[position];
                    player1 = mammals1[position];
                    player2 = mammals2[position];
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(v)
                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
                                    mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();
                        /*if (ActualName1.equals("") || ActualName1.equals(null)) {
                            ActualName1 = Categories.p1Name;
                        }
                        if (ActualName2.equals("") || ActualName2.equals(null)) {
                            ActualName2 = Categories.p2Name;
                        }*/
                                    if (Categories.which == 1) {
                                        Intent in = new Intent(getActivity(), OnePlayerActivity.class);
                                        in.putExtra(Categories.PLAYER_1, player1);
                                        in.putExtra(Categories.PLAYER_2, player2);
                                        in.putExtra(Categories.PLAYER_1_NAME, name1);
                                        in.putExtra(Categories.PLAYER_2_NAME, name2);
                                        in.putExtra(Categories.PLAYER_1_SOUND, player1sound);
                                        in.putExtra(Categories.PLAYER_2_SOUND, player2sound);
                                        in.putExtra(Categories.ACTUAL_NAME_1, mainApplication.PLAYERNAME);
                                        in.putExtra(Categories.ACTUAL_NAME_2, mainApplication.COMPUTERNAME);
                                        startActivity(in);
                                        getActivity().finish();
                                    }
                                    if (Categories.which == 2) {
                                        Intent in = new Intent(getActivity(), TwoPlayerActivity.class);
                                        in.putExtra(Categories.PLAYER_1, player1);
                                        in.putExtra(Categories.PLAYER_2, player2);
                                        in.putExtra(Categories.PLAYER_1_NAME, name1);
                                        in.putExtra(Categories.PLAYER_2_NAME, name2);
                                        in.putExtra(Categories.PLAYER_1_SOUND, player1sound);
                                        in.putExtra(Categories.PLAYER_2_SOUND, player2sound);
                                        in.putExtra(Categories.ACTUAL_NAME_1, mainApplication.PLAYERNAME);
                                        in.putExtra(Categories.ACTUAL_NAME_2, mainApplication.COMPUTERNAME);
                                        startActivity(in);
                                        getActivity().finish();
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    clicked = false;
                                    dialog.dismiss();
                                }
                            })
                            .setNeutralButton("Switch", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!clicked) {

                                iv1.setBackgroundResource(mammals2[position]);
                                iv2.setBackgroundResource(mammals1[position]);
                                player1 = mammals2[position];
                                player2 = mammals1[position];
                                name1 = mammalNames2[position];
                                name2 = mammalNames1[position];
                                player1sound = sound2[position];
                                player2sound = sound1[position];
                                clicked = true;
                            } else {

                                iv1.setBackgroundResource(mammals1[position]);
                                iv2.setBackgroundResource(mammals2[position]);
                                player1 = mammals1[position];
                                player2 = mammals2[position];
                                name1 = mammalNames1[position];
                                name2 = mammalNames2[position];
                                player1sound = sound1[position];
                                player2sound = sound2[position];
                                clicked = false;
                            }
                        }
                    });
                }
            });
            return mammalsHolder;
        }

        @Override
        public void onBindViewHolder(MammalsHolder mHolder, int position) {

            mHolder.mImageView1.setBackgroundResource(mammals1[position]);
            mHolder.mImageView2.setBackgroundResource(mammals2[position]);
            mHolder.mTextView.setText("" + mammalNames1[position] + " & " + mammalNames2[position]);
        }

        @Override
        public int getItemCount() {
            return mammals1.length;
        }

        public class MammalsHolder extends RecyclerView.ViewHolder {
            ImageView mImageView1, mImageView2;
            TextView mTextView;

            public MammalsHolder(View v) {
                super(v);
                mImageView1 = (ImageView) v.findViewById(R.id.imageView);
                mImageView2 = (ImageView) v.findViewById(R.id.imageView2);
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }
    }

}