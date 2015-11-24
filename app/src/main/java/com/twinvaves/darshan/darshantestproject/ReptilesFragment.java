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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReptilesFragment extends Fragment {

    RecyclerView mListView;

    int[] reptiles1 = {R.drawable.lizard, R.drawable.tortoise};
    int[] reptiles2 = {R.drawable.snake, R.drawable.crocodile};
    String[] reptileNames1 = {"Lizard", "Tortoise"};
    String[] reptileNames2 = {"Snake", "Crocodile"};
    int[] sound1 = {R.raw.lizard, R.raw.tortoise};
    int[] sound2 = {R.raw.snake, R.raw.crocodile};
    boolean clicked = false;
    int player1, player2;
    String name1, name2;
    int player1sound, player2sound;
//    String ActualName1, ActualName2;


    public ReptilesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.fragment_reptiles, container, false);
        /*mListView = (ListView) mView.findViewById(R.id.reptilesListView);
        mListView.setAdapter(new ReptilesListViewAdapter());
        mListView.setOnItemClickListener(this);*/
        mListView = (RecyclerView) mView.findViewById(R.id.reptilesListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(new MyReptilesAdapter());
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
//        iv1.setBackgroundResource(reptiles1[position]);
//        iv2.setBackgroundResource(reptiles2[position]);
//        player1 = reptiles1[position];
//        player2 = reptiles2[position];
//        player1sound = sound1[position];
//        player2sound = sound2[position];
//        name1 = reptileNames1[position];
//        name2 = reptileNames2[position];
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
//                    iv1.setBackgroundResource(reptiles2[position]);
//                    iv2.setBackgroundResource(reptiles1[position]);
//                    player1 = reptiles2[position];
//                    player2 = reptiles1[position];
//                    name1 = reptileNames2[position];
//                    name2 = reptileNames1[position];
//                    player1sound = sound2[position];
//                    player2sound = sound1[position];
//                    clicked = true;
//                } else {
//
//                    iv1.setBackgroundResource(reptiles1[position]);
//                    iv2.setBackgroundResource(reptiles2[position]);
//                    player1 = reptiles1[position];
//                    player2 = reptiles2[position];
//                    player1sound = sound1[position];
//                    player2sound = sound2[position];
//                    name1 = reptileNames1[position];
//                    name2 = reptileNames2[position];
//                    clicked = false;
//                }
//            }
//        });
//    }


//    public class ReptilesListViewAdapter extends BaseAdapter {
//
//
//        @Override
//        public int getCount() {
//            return reptiles1.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return reptiles1[position];
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
//            mHolder.mImageView1.setBackgroundResource(reptiles1[position]);
//            mHolder.mImageView2.setBackgroundResource(reptiles2[position]);
//            mHolder.mTextView.setText("" + reptileNames1[position] + " & " + reptileNames2[position]);
//            return convertView;
//        }
//    }

    public class MyReptilesAdapter extends RecyclerView.Adapter<MyReptilesAdapter.ReptilesHolder> {
        @Override
        public ReptilesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater(null).inflate(R.layout.single_view, null);
            ReptilesHolder reptilesHolder = new ReptilesHolder(view);
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
                    iv1.setBackgroundResource(reptiles1[position]);
                    iv2.setBackgroundResource(reptiles2[position]);
                    player1 = reptiles1[position];
                    player2 = reptiles2[position];
                    player1sound = sound1[position];
                    player2sound = sound2[position];
                    name1 = reptileNames1[position];
                    name2 = reptileNames2[position];
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

                                iv1.setBackgroundResource(reptiles2[position]);
                                iv2.setBackgroundResource(reptiles1[position]);
                                player1 = reptiles2[position];
                                player2 = reptiles1[position];
                                name1 = reptileNames2[position];
                                name2 = reptileNames1[position];
                                player1sound = sound2[position];
                                player2sound = sound1[position];
                                clicked = true;
                            } else {

                                iv1.setBackgroundResource(reptiles1[position]);
                                iv2.setBackgroundResource(reptiles2[position]);
                                player1 = reptiles1[position];
                                player2 = reptiles2[position];
                                player1sound = sound1[position];
                                player2sound = sound2[position];
                                name1 = reptileNames1[position];
                                name2 = reptileNames2[position];
                                clicked = false;
                            }
                        }
                    });
                }
            });
            return reptilesHolder;
        }

        @Override
        public void onBindViewHolder(ReptilesHolder mHolder, int position) {
            mHolder.mImageView1.setBackgroundResource(reptiles1[position]);
            mHolder.mImageView2.setBackgroundResource(reptiles2[position]);
            mHolder.mTextView.setText("" + reptileNames1[position] + " & " + reptileNames2[position]);
        }

        @Override
        public int getItemCount() {
            return reptiles1.length;
        }

        public class ReptilesHolder extends RecyclerView.ViewHolder {
            ImageView mImageView1, mImageView2;
            TextView mTextView;

            public ReptilesHolder(View v) {
                super(v);
                mImageView1 = (ImageView) v.findViewById(R.id.imageView);
                mImageView2 = (ImageView) v.findViewById(R.id.imageView2);
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }
    }

}

