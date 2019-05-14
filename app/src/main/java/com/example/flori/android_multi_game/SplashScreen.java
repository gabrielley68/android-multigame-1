package com.example.flori.android_multi_game;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.flori.android_multi_game.utils.GameUtils;

public class SplashScreen extends AppCompatActivity {
    private static int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GameUtils.launchView(SplashScreen.this, CreatePlayerActivity.class, true);
            }
        }, TIME_OUT);
    }
}
