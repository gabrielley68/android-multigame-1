package com.example.flori.android_multi_game.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.utils.GameUtils;


public class SwipeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fast_tap, container, false);
        final Fragment fragmentInGame = new FastTapFragmentInGame();

        final RelativeLayout frameLayout = view.findViewById(R.id.fragment_fasttap);
        final Button start = view.findViewById(R.id.fragment_fasttap_start);

        TextView gameName = view.findViewById(R.id.fragment_fasttap_game_txt);
        gameName.setText("Swipe");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FastTapFragmentInGame fragment = new FastTapFragmentInGame();
                Bundle bundle = new Bundle();
                bundle.putString("GAME", "swipe");
                fragment.setArguments(bundle);
                GameUtils.addFragmentToFragment(SwipeFragment.this, fragment, frameLayout.getId());
            }
        });

        return view;
    }
}