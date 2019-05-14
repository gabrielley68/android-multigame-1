package com.example.flori.android_multi_game;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

public class CreatePlayerActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PICTURE = 1;
    private static final int REQUEST_LOCATION = 10;

    private ImageView player_image;
    private ImageView player_localisation_gps;

    private TextView player_name;
    private TextView player_firstname;
    private TextView player_age;
    private TextView player_localisation;

    private Location currentlocation;
    private String locationstringify;
    private String pathImg;

    private Button btn_players;
    private Button btn_validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player_activity);

        player_image = findViewById(R.id.create_player_image);
        player_localisation_gps = findViewById(R.id.create_player_localisation_gps);

        player_name = findViewById(R.id.create_player_name);
        player_firstname = findViewById(R.id.create_player_firstname);
        player_age = findViewById(R.id.create_player_age);
        player_localisation = findViewById(R.id.create_player_localisation);

        btn_players = findViewById(R.id.create_player_btn_players);
        btn_validate = findViewById(R.id.create_player_btn_validate);

//        for(int i =0; i <=50; i++){
//            AddPlayerInDb(new Player("name"+i,"paul",24,"annecy","path"));
//        }
//        getAllPlayers();


        player_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(Intent.createChooser(intent, "Choix de la photo"), REQUEST_PICK_PICTURE);
            }
        });

        player_localisation_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    getUserLocation();
                }
            }
        });

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean error = false;
                if (player_name.getText().toString().equals("")) {
                    Toast.makeText(CreatePlayerActivity.this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if (player_firstname.getText().toString().equals("")) {
                    Toast.makeText(CreatePlayerActivity.this, "You did not enter a firstname", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if (player_age.getText().toString().equals("")) {
                    Toast.makeText(CreatePlayerActivity.this, "You did not enter an age", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if (player_localisation.getText().toString().equals("") && currentlocation == null) {
                    Toast.makeText(CreatePlayerActivity.this, "You did not enter a location", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if (pathImg == null) {
                    Toast.makeText(CreatePlayerActivity.this, "You did not enter an image", Toast.LENGTH_SHORT).show();
                    error = true;
                }
                if (error == false) {
                    if (currentlocation != null) {
                        locationstringify = currentlocation.getLatitude() + "-" + currentlocation.getLatitude();
                    } else {
                        locationstringify = player_localisation.getText().toString();
                    }
                    Player unplayer = new Player(player_name.getText().toString(), player_firstname.getText().toString(), Integer.parseInt(player_age.getText().toString()), locationstringify, pathImg);
                    String a = "reussie";
                    AddPlayerInDb(unplayer);
                    GameUtils.launchView(CreatePlayerActivity.this, MainActivity.class, true);

                    PlayerManager.getInstance().setPlayer(unplayer);
                }
            }
        });

        btn_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameUtils.launchView(CreatePlayerActivity.this, ShowPlayerActivity.class, true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_PICTURE && resultCode == RESULT_OK) {
            pathImg = data.getData().getPath();
            Picasso.get().load(data.getData()).into(player_image);
        }
    }

    public void AddPlayerInDb(Player player) {
        Realm mRealmInstance = Realm.getDefaultInstance();
        mRealmInstance.beginTransaction();
        try {
            mRealmInstance.copyToRealmOrUpdate(player);
            mRealmInstance.commitTransaction();
        } catch (Exception e) {
            String a = "";
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {

            if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

                    && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                getUserLocation();

            }

        }
    }

    @SuppressLint("MissingPermission")
    private void getUserLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

            @Override

            public void onLocationChanged(Location location) {

                System.out.println("La location de l'utilisateur est :" + location.getLatitude() + " : " + location.getLongitude());
                String a = "";
                currentlocation = location;
            }

            @Override

            public void onStatusChanged(String provider, int status, Bundle extras) {

                String b = "";
            }

            @Override

            public void onProviderEnabled(String provider) {
                String c = "";
            }

            @Override

            public void onProviderDisabled(String provider) {

                String d = "";
            }

        }, null);

    }
}
