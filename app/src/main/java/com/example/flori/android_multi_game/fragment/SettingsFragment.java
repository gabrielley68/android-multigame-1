package com.example.flori.android_multi_game.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flori.android_multi_game.R;

public class SettingsFragment extends Fragment {
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //Instancier vos composants graphique ici (faîtes vos findViewById)

        return view;
    }

}