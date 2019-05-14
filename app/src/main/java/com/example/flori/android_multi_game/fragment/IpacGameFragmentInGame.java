package com.example.flori.android_multi_game.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.EndGameActivity;
import com.example.flori.android_multi_game.MainActivity;
import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.utils.GameUtils;

public class IpacGameFragmentInGame extends Fragment {

    private static final int NUMBER_OF_TRY = 10;
    private EditText numberEd;
    private TextView numberOfTry;
    private TextView moreOrLess;
    private Button validate;
    private Boolean mybool;
    private Intent intent;
    private int numberRemaining = NUMBER_OF_TRY;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ipac_game, container, false);
        final RelativeLayout dragndrop = view.findViewById(R.id.fragment_ipacgame_ingame);

        numberOfTry = view.findViewById(R.id.main_number_try);
        numberEd = view.findViewById(R.id.main_number);
        moreOrLess = view.findViewById(R.id.main_more);
        validate = view.findViewById(R.id.main_validate);

        final int numberToFind = (int) (Math.random() * 100);
        numberOfTry.setText(getResources().getString(R.string.number_try, numberRemaining));

        numberEd.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validate.performClick();
                    return true;
                }
                return false;
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!numberEd.getText().toString().isEmpty()) {
                    int number = Integer.valueOf(numberEd.getText().toString());

                    InputMethodManager inputManager = (InputMethodManager)
                            ((MainActivity) getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow( ((MainActivity) getActivity()).getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                    numberEd.getText().clear();

                    Animation animFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                    moreOrLess.startAnimation(animFadeOut);
                    Animation animFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    moreOrLess.startAnimation(animFadeIn);


                    if (number == numberToFind) {
                        Intent intent = new Intent(getActivity(), EndGameActivity.class);
                        intent.putExtra("SCORE", numberRemaining);
                        GameUtils.launchView((AppCompatActivity) getActivity(), intent, false);
                        IpacGameFragmentInGame.this.getFragmentManager().popBackStack();
                    } else {
                        numberRemaining--;
                        if (numberRemaining == 0) {
                            Intent intent = new Intent(getActivity(), EndGameActivity.class);
                            intent.putExtra("SCORE", numberRemaining);
                            GameUtils.launchView((AppCompatActivity) getActivity(), intent, false);
                            IpacGameFragmentInGame.this.getFragmentManager().popBackStack();
                        }

                        numberOfTry.setText(getResources().getString(R.string.number_try, numberRemaining));
                        moreOrLess.setText(getResources().getString(number < numberToFind ? R.string.number_info_text_plus : R.string.number_info_text_less));
                    }
                }
            }
        });

        return view;
    }

}