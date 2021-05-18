package com.example.heiroghliphics_translate_project.SplashScreensFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPager extends FragmentPagerAdapter {
    public ViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new SplashOneFragment();
            case 1: return new SplashTwoFragment();
            case 2: return new SplashThreeFragment();
            case 3: return new SplashFourFragment();
            case 4: return new SplashFiveFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
