package com.example.flori.android_multi_game;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.flori.android_multi_game.fragment.FastTapFragment;
import com.example.flori.android_multi_game.fragment.FastTapFragmentInGame;
import com.example.flori.android_multi_game.fragment.IpacGameFragment;
import com.example.flori.android_multi_game.fragment.SettingsFragment;
import com.example.flori.android_multi_game.utils.CustomViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    public CustomViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.activity_main_pager);
        tabLayout = findViewById(R.id.activity_main_tab_layout);

        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(this);

        final ArrayList<Fragment> fragments = new ArrayList<>();

        Bundle bundle3 = new Bundle();
        bundle3.putString("MENU", "dragndrop");
        FastTapFragment fragmentStart3 = new FastTapFragment();
        fragmentStart3.setArguments(bundle3);
        fragments.add(fragmentStart3);

        Bundle bundle2 = new Bundle();
        bundle2.putString("MENU", "swipe");
        FastTapFragment fragmentStart2 = new FastTapFragment();
        fragmentStart2.setArguments(bundle2);
        fragments.add(fragmentStart2);

        Bundle bundle = new Bundle();
        bundle.putString("MENU", "fasttap");
        FastTapFragment fragmentStart = new FastTapFragment();
        fragmentStart.setArguments(bundle);
        fragments.add(fragmentStart);

        fragments.add(new IpacGameFragment());
        fragments.add(new SettingsFragment());

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "DragNDrop";
                    case 1:

                        return "Swipe";
                    case 2:

                        return "FastTap";
                    case 3:

                        return "IpacGame";
                    case 4:

                        return "Settings";
                    case 5:

                    default:
                        return "";
                }
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            for (Fragment subFragment : fragment.getChildFragmentManager().getFragments()) {
                if (subFragment instanceof FastTapFragmentInGame) {
                    subFragment.getFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
