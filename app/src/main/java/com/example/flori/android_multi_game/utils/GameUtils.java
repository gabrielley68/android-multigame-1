package com.example.flori.android_multi_game.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.flori.android_multi_game.R;


public class GameUtils {

    public static void launchView(AppCompatActivity activity, Class cls) {
        launchView(activity, cls, true);
    }

    public static void launchView(AppCompatActivity activity, Intent intent) {
        launchView(activity, intent, true);
    }

    public static void launchView(AppCompatActivity activity, Class cls, boolean finish) {
        Intent intent = new Intent(activity, cls);
        launchView(activity, intent, finish);
    }

    public static void launchView(AppCompatActivity activity, Intent intent, boolean finish) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        if (finish) {
            activity.finish();
        }
    }

    public static void addFragmentToActivity(AppCompatActivity activity, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction;
        transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, "tag");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragmentToFragment(Fragment parentFragment, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction;
        transaction = parentFragment.getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.add(frameId, fragment, "tag");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
