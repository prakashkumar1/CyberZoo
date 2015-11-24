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
public class AnimalsFragment extends Fragment {

    RecyclerView mListView;

    int[] animals1 = {R.drawable.tiger_edit, R.drawable.horse, R.drawable.bear_edit, R.drawable.pig, R.drawable.rhinoceros};
    int[] animals2 = {R.drawable.lion_edit, R.drawable.zebra, R.drawable.wolf_edit, R.drawable.donkey, R.drawable.hippopotamus};
    String[] animalsNames1 = {"Tiger", "Horse", "Bear", "Pig", "Rhinoceros"};
    String[] animalsNames2 = {"Lion", "Zebra", "Wolf", "Donkey", "Hippopotamus"};
    int[] sound1 = {R.raw.tiger, R.raw.horse, R.raw.bear, R.raw.pig, R.raw.rhinoceros};
    int[] sound2 = {R.raw.lion, R.raw.zebra, R.raw.wolf, R.raw.donkey, R.raw.hippopotamus};
    boolean clicked = false;
    int player1, player2;
    String name1, name2;
    int player1sound, player2sound;
    //    String ActualName1, ActualName2;


    public AnimalsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.fragment_animals, container, false);
       /* mListView = (ListView) mView.findViewById(R.id.animalsListView);
        mListView.setAdapter(new AnimalsListViewAdapter());
        mListView.setOnItemClickListener(this);*/

        mListView = (RecyclerView) mView.findViewById(R.id.animalsListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(new MyAnimalsAdapter());

        return mView;
    }
//
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
//        iv1.setBackgroundResource(animals1[position]);
//        iv2.setBackgroundResource(animals2[position]);
//        player1 = animals1[position];
//        player2 = animals2[position];
//        player1sound = sound1[position];
//        player2sound = sound2[position];
//        name1 = animalsNames1[position];
//        name2 = animalsNames2[position];
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(v)
//                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
//                        mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();
//                        /*if (ActualName1 != null || ActualName2 != null) {
//                            FirstScreen.newCompName = tvPlayer2.getText().toString();
//                            FirstScreen.newPlayerName = tvPlayer1.getText().toString();
//                        }*/
//
//                       /* if (ActualName1.equals("") || ActualName1.equals(null)) {
//                            ActualName1 = Categories.p1Name;
//
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
//                    iv1.setBackgroundResource(animals2[position]);
//                    iv2.setBackgroundResource(animals1[position]);
//                    player1 = animals2[position];
//                    player2 = animals1[position];
//                    name1 = animalsNames2[position];
//                    name2 = animalsNames1[position];
//                    player1sound = sound2[position];
//                    player2sound = sound1[position];
//                    clicked = true;
//                } else {
//
//                    iv1.setBackgroundResource(animals1[position]);
//                    iv2.setBackgroundResource(animals2[position]);
//                    player1 = animals1[position];
//                    player2 = animals2[position];
//                    player1sound = sound1[position];
//                    player2sound = sound2[position];
//                    name1 = animalsNames1[position];
//                    name2 = animalsNames2[position];
//                    clicked = false;
//                }
//            }
//        });
//    }


//    public class AnimalsListViewAdapter extends BaseAdapter {
//
//
//        @Override
//        public int getCount() {
//            return animals1.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return animals1[position];
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
//            mHolder.mImageView1.setBackgroundResource(animals1[position]);
//            mHolder.mImageView2.setBackgroundResource(animals2[position]);
//            mHolder.mTextView.setText("" + animalsNames1[position] + " & " + animalsNames2[position]);
//            return convertView;
//        }
//    }

    public class MyAnimalsAdapter extends RecyclerView.Adapter<MyAnimalsAdapter.AnimalsHolder> {
        @Override
        public AnimalsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater(null).inflate(R.layout.single_view, null);
            AnimalsHolder holder = new AnimalsHolder(view);
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
                    iv1.setBackgroundResource(animals1[position]);
                    iv2.setBackgroundResource(animals2[position]);
                    player1 = animals1[position];
                    player2 = animals2[position];
                    player1sound = sound1[position];
                    player2sound = sound2[position];
                    name1 = animalsNames1[position];
                    name2 = animalsNames2[position];
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(v)
                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mainApplication.PLAYERNAME = tvPlayer1.getText().toString();
                                    mainApplication.COMPUTERNAME = tvPlayer2.getText().toString();
                        /*if (ActualName1 != null || ActualName2 != null) {
                            FirstScreen.newCompName = tvPlayer2.getText().toString();
                            FirstScreen.newPlayerName = tvPlayer1.getText().toString();
                        }*/

                       /* if (ActualName1.equals("") || ActualName1.equals(null)) {
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

                                iv1.setBackgroundResource(animals2[position]);
                                iv2.setBackgroundResource(animals1[position]);
                                player1 = animals2[position];
                                player2 = animals1[position];
                                name1 = animalsNames2[position];
                                name2 = animalsNames1[position];
                                player1sound = sound2[position];
                                player2sound = sound1[position];
                                clicked = true;
                            } else {

                                iv1.setBackgroundResource(animals1[position]);
                                iv2.setBackgroundResource(animals2[position]);
                                player1 = animals1[position];
                                player2 = animals2[position];
                                player1sound = sound1[position];
                                player2sound = sound2[position];
                                name1 = animalsNames1[position];
                                name2 = animalsNames2[position];
                                clicked = false;
                            }
                        }
                    });
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(AnimalsHolder mHolder, int position) {
            mHolder.mImageView1.setBackgroundResource(animals1[position]);
            mHolder.mImageView2.setBackgroundResource(animals2[position]);
            mHolder.mTextView.setText("" + animalsNames1[position] + " & " + animalsNames2[position]);
        }

        @Override
        public int getItemCount() {
            return animals1.length;
        }

        public class AnimalsHolder extends RecyclerView.ViewHolder {
            ImageView mImageView1, mImageView2;
            TextView mTextView;

            public AnimalsHolder(View v) {
                super(v);
                mImageView1 = (ImageView) v.findViewById(R.id.imageView);
                mImageView2 = (ImageView) v.findViewById(R.id.imageView2);
                mTextView = (TextView) v.findViewById(R.id.textView);
            }
        }
    }

}
