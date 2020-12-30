package com.dumichelle.lifestyleapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomePageAdapter extends FragmentStateAdapter {

    String username;

    public HomePageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                HomeFragment myObj = new HomeFragment();
                myObj.setArguments(bundle);
                return myObj;
            case 1:
                return new SleepFragment();
            case 2:
                return new DrinkFragment();
            case 3:
                return new FoodFragment();
            case 4:
                return new ExerciseFragment();
            default:
                return new ResourcesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
