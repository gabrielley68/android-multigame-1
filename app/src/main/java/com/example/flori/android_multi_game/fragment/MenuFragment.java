package com.example.flori.android_multi_game.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.utils.GameUtils;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fast_tap, container, false);

        final RelativeLayout frameLayout = view.findViewById(R.id.fragment_fasttap);
        final Button start = view.findViewById(R.id.fragment_fasttap_start);

        final String menu = getArguments().getString("MENU");

        TextView gameName = view.findViewById(R.id.fragment_fasttap_game_txt);

        if (menu.equals("fasttap")) {
            gameName.setText("Fast Tap !");
        }
        if (menu.equals("swipe")) {
            gameName.setText("Swipe !");
        }
        if (menu.equals("dragndrop")) {
            gameName.setText("Drag N Drop !");
        }
        if (menu.equals("IpacGame")) {
            gameName.setText("IPAC Game !");
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.equals("fasttap")) {
                    FastTapFragmentInGame fragment = new FastTapFragmentInGame();
                    Bundle bundle = new Bundle();
                    bundle.putString("GAME", "fasttap");
                    fragment.setArguments(bundle);
                    GameUtils.addFragmentToFragment(MenuFragment.this, fragment, frameLayout.getId());
                }
                if (menu.equals("swipe")) {
                    FastTapFragmentInGame fragment = new FastTapFragmentInGame();
                    Bundle bundle = new Bundle();
                    bundle.putString("GAME", "swipe");
                    fragment.setArguments(bundle);
                    GameUtils.addFragmentToFragment(MenuFragment.this, fragment, frameLayout.getId());
                }

                if (menu.equals("dragndrop")) {
                    DragnDropFragmentInGame fragment = new DragnDropFragmentInGame();
                    GameUtils.addFragmentToFragment(MenuFragment.this, fragment, frameLayout.getId());
                }

                if (menu.equals("IpacGame")) {
                    IpacGameFragmentInGame fragment = new IpacGameFragmentInGame();
                    GameUtils.addFragmentToFragment(MenuFragment.this, fragment, frameLayout.getId());
                }
            }
        });

        return view;
    }
}