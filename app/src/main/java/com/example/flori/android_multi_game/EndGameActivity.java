package com.example.flori.android_multi_game;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class EndGameActivity extends AppCompatActivity {

    private int monScore;
    private TextView score;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

          score = findViewById(R.id.endgame_score);
          monScore = getIntent().getIntExtra("SCORE", 0);
          score.setText("Votre score est de : " + monScore);

          Button leave = (Button) findViewById(R.id.endgame_leave);
          leave.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  // TODO Auto-generated method stub
                  finish();
                  System.exit(0);
              }
          });

          Button restart = (Button) findViewById(R.id.endgame_menu);
          restart.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  finish();
              }
          });
    }
}
