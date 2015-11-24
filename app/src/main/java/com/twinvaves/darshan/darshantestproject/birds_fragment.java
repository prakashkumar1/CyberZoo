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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class birds_fragment extends Fragment{


    public birds_fragment() {
        // Required empty public constructor
    }


    //    ListView mListView;
    RecyclerView recyclerView;
    int[] birds1 = {R.drawable.grouse, R.drawable.owl_edit, R.drawable.eagle, R.drawable.duck, R.drawable.bat, R.drawable.dove};
    int[] birds2 = {R.drawable.quail, R.drawable.killdeep_edit, R.drawable.falcon_edit, R.drawable.rooster, R.drawable.white_crow, R.drawable.pigeon};
    String[] birdsNames1 = {"Grouse", "Owl", "Eagle", "Duck", "Bat", "Dove"};
    String[] birdsNames2 = {"Quail", "Killdeer", "Falcon", "Rooster", "White Crow", "Pigeon"};
    int[] sound1 = {R.raw.grouse, R.raw.owl, R.raw.eagle, R.raw.duck, R.raw.bat, R.raw.dove};
    int[] sound2 = {R.raw.quail_bobwhite, R.raw.killdeer, R.raw.falcon, R.raw.rooster, R.raw.white_crow, R.raw.pigeon};
    boolean clicked = false;
    int player1, player2;
    int player1sound, player2sound;
    String name1, name2;
    String ActualName1, ActualName2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.birds_fragment, container, false);
//        mListView = (ListView) mView.findViewById(R.id.birdsListView);
//        mListView.setAdapter(new listViewAdapter());
//        mListView.setOnItemClickListener(this);
        recyclerView = (RecyclerView) mView.findViewById(R.id.birdsListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter());

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
//
//        iv1.setBackgroundResource(birds1[position]);
//        iv2.setBackgroundResource(birds2[position]);
//        name1 = birdsNames1[position];
//        name2 = birdsNames2[position];
//        player1sound = sound1[position];
//        player2sound = sound2[position];
//        player1 = birds1[position];
//        player2 = birds2[position];
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
//                    iv1.setBackgroundResource(birds2[position]);
//                    iv2.setBackgroundResource(birds1[position]);
//                    player1 = birds2[position];
//                    player2 = birds1[position];
//                    name1 = birdsNames2[position];
//                    name2 = birdsNames1[position];
//                    player1sound = sound2[position];
//                    player2sound = sound1[position];
//                    clicked = true;
//                } else {
//
//                    iv1.setBackgroundResource(birds1[position]);
//                    iv2.setBackgroundResource(birds2[position]);
//                    player1 = birds1[position];
//                    player2 = birds2[position];
//                    name1 = birdsNames1[position];
//                    name2 = birdsNames2[position];
//                    player1sound = sound1[position];
//                    player2sound = sound2[position];
//                    clicked = false;
//                }
//            }
//        });
//    }
//

//    public class listViewAdapter extends BaseAdapter {
//
//
//        @Override
//        public int getCount() {
//            return birds1.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return birds1[position];
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
//
//            mHolder.mImageView1.setBackgroundResource(birds1[position]);
//            mHolder.mImageView2.setBackgroundResource(birds2[position]);
//            mHolder.mTextView.setText("" + birdsNames1[position] + " & " + birdsNames2[position]);
//
//            return convertView;
//        }
//
//
//    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
        @Override
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater(null).inflate(R.layout.single_view, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = recyclerView.getChildPosition(view);
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

                    iv1.setBackgroundResource(birds1[position]);
                    iv2.setBackgroundResource(birds2[position]);
                    name1 = birdsNames1[position];
                    name2 = birdsNames2[position];
                    player1sound = sound1[position];
                    player2sound = sound2[position];
                    player1 = birds1[position];
                    player2 = birds2[position];
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(v)
                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
                                    mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();

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

                                iv1.setBackgroundResource(birds2[position]);
                                iv2.setBackgroundResource(birds1[position]);
                                player1 = birds2[position];
                                player2 = birds1[position];
                                name1 = birdsNames2[position];
                                name2 = birdsNames1[position];
                                player1sound = sound2[position];
                                player2sound = sound1[position];
                                clicked = true;
                            } else {

                                iv1.setBackgroundResource(birds1[position]);
                                iv2.setBackgroundResource(birds2[position]);
                                player1 = birds1[position];
                                player2 = birds2[position];
                                name1 = birdsNames1[position];
                                name2 = birdsNames2[position];
                                player1sound = sound1[position];
                                player2sound = sound2[position];
                                clicked = false;
                            }
                        }
                    });
                }
            });
            Holder mHolder = new Holder(view);
            return mHolder;
        }

        @Override
        public void onBindViewHolder(Holder mHolder, int position) {
            mHolder.mImageView1.setBackgroundResource(birds1[position]);
            mHolder.mImageView2.setBackgroundResource(birds2[position]);
            mHolder.mTextView.setText("" + birdsNames1[position] + " & " + birdsNames2[position]);
        }

        @Override
        public int getItemCount() {
            return birds1.length;
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView mImageView1, mImageView2;
            TextView mTextView;

            public Holder(View v) {
                super(v);
                mImageView1 = (ImageView) v.findViewById(R.id.imageView);
                mImageView2 = (ImageView) v.findViewById(R.id.imageView2);
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }
    }


}
