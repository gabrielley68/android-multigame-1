package com.example.flori.android_multi_game.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.CreatePlayerActivity;
import com.example.flori.android_multi_game.EndGameActivity;
import com.example.flori.android_multi_game.MainActivity;
import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.ShowPlayerActivity;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.example.flori.android_multi_game.utils.CustomViewPager;
import com.example.flori.android_multi_game.utils.TimerUtils;

import org.w3c.dom.Text;

import java.util.Random;

public class FastTapFragmentInGame extends Fragment implements GestureDetector.OnGestureListener {
    private int scoreTotal = 0;
    private int numbermin;
    private int numbermax;
    private int number;
    private GestureDetector gestureDetector;
    private TimerUtils timerUtils;

    private TextView tap;
    private TextView score;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fast_tap_ingame, container, false);

        final RelativeLayout fasttapcontainer = view.findViewById(R.id.fragment_fasttap_ingame);
        score = view.findViewById(R.id.fragment_fasttap_score);
        tap = view.findViewById(R.id.fragment_fasttap_tap);


        String game = getArguments().getString("GAME");
        if (game.equals("fasttap")) {
            LaunchClickGame(inflater, container, view, fasttapcontainer, score, tap);
        }

        if (game.equals("swipe")) {
            ((MainActivity) getActivity()).viewPager.setPagingEnabled(false);

            LaunchSwipeGame(inflater, container, view, fasttapcontainer, score, tap);
        }

        return view;
    }


    //J'ai revu la fonction pour qu'on puisse arrêter le timer
    public void startTimer(LayoutInflater inflater, ViewGroup container, View view) {

        final TextView timer = view.findViewById(R.id.fragment_fasttap_time);

//        new CountDownTimer(30000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                timer.setText("Temps restant : " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//
//                Intent intent = new Intent(getActivity(), EndGameActivity.class);
//                intent.putExtra("SCORE", scoreTotal);
//                GameUtils.launchView((AppCompatActivity) getActivity(), intent, false);
//                FastTapFragmentInGame.this.getFragmentManager().popBackStack();
//            }
//        }.start();

        timerUtils = new TimerUtils(timer, 30) {
            @Override
            public void end() {
                Intent intent = new Intent(getActivity(), EndGameActivity.class);
                intent.putExtra("SCORE", scoreTotal);
                GameUtils.launchView((AppCompatActivity) getActivity(), intent, false);
                FastTapFragmentInGame.this.getFragmentManager().popBackStack();
            }
        };
        timerUtils.beginTimer();
    }

    public int getRandomNumber(int first, int second) {
        int min = first;
        int max = second;

        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    public void LaunchClickGame(LayoutInflater inflater, ViewGroup container, View view,
                                RelativeLayout fasttapcontainer, final TextView score, final TextView tap) {
        numbermin = 0;
        numbermax = 1;


        number = getRandomNumber(numbermin, numbermax);

        startTimer(inflater, container, view);

        if (number == 0) {
            tap.setText("Tap");
        }
        if (number == 1) {
            tap.setText("Looong tap");
        }

        fasttapcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 0) {
                    tap.setText("Tap");
                    scoreTotal = scoreTotal + 1;
                    score.setText("Score : " + scoreTotal);
                    number = getRandomNumber(numbermin, numbermax);

                    if (number == 0) {
                        tap.setText("Tap");
                    } else {
                        tap.setText("Looong tap");
                    }
                } else {
                    tap.setText("Looong tap");
                }
            }
        });


        fasttapcontainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (number == 1) {
                    tap.setText("Looong tap");
                    scoreTotal = scoreTotal + 5;
                    score.setText("Score : " + scoreTotal);
                    number = getRandomNumber(numbermin, numbermax);
                    if (number == 0) {
                        tap.setText("Tap");
                    } else {
                        tap.setText("Looong tap");
                    }
                    return true;
                } else {
                    tap.setText("Tap");
                    return false;
                }

            }
        });
    }

    public void LaunchSwipeGame(LayoutInflater inflater, ViewGroup container, View view,
                                RelativeLayout fasttapcontainer, final TextView score, final TextView tap) {

        numbermin = 0;
        numbermax = 3;
        gestureDetector = new GestureDetector(getContext(), this);


        generate();
        startTimer(inflater, container, view);


        View.OnTouchListener gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        fasttapcontainer.setOnTouchListener(gestureListener);

//            scoreTotal = scoreTotal + 1;
//            score.setText("Score : " + scoreTotal);
//            number = getRandomNumber(numbermin, numbermax);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {


        if (motionEvent1.getY() - motionEvent2.getY() > 50) {
            if (number == 2) {
                //Swipe up
                generate();
                addScore();
                return true;
            }
        } else if (motionEvent2.getY() - motionEvent1.getY() > 50) {
            if (number == 3) {
                //Swipe down
                generate();
                addScore();
                return true;
            }
        } else if (motionEvent1.getX() - motionEvent2.getX() > 50) {
            if (number == 0) {
                //Swipe left
                generate();
                addScore();
                return true;
            }

        } else if (motionEvent2.getX() - motionEvent1.getX() > 50) {
            if (number == 1) {
                //Swipe right
                generate();
                addScore();
                return true;
            }
        }
        return false;
    }

    public void generate() {
        number = getRandomNumber(numbermin, numbermax);

        if (number == 0) {
            tap.setText("Swipe LEFT");
        }
        if (number == 1) {
            tap.setText("Swipe RIGHT");
        }
        if (number == 2) {
            tap.setText("Swipe UP");
        }
        if (number == 3) {
            tap.setText("Swipe DOWN");
        }
    }

    public void addScore() {
        scoreTotal = scoreTotal + 1;
        score.setText("Score : " + scoreTotal);
    }

    //Gab : J'ai bouger le setpagingenabled ici pour qu'on puisse swipe si on change d'onglet
    @Override
    public void onDestroy() {
        super.onDestroy();
        timerUtils.stopTimer();
        ((MainActivity) getActivity()).viewPager.setPagingEnabled(true);
    }
}