package com.dumichelle.lifestyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View.OnClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity2 extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        username = getIntent().getStringExtra("username");
        HomePageAdapter h = new HomePageAdapter(this);
        h.username = username;
        viewPager2.setAdapter(h);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabLayoutMediator tabLayoutMediator  = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position) {
                    case 0: {
                        tab.setText("Home");
                        tab.setIcon(R.drawable.ic_home);
                        break;
                    }
                    case 1: {
                        tab.setText("Sleep");
                        tab.setIcon(R.drawable.ic_sleep);
                        break;
                    }
                    case 2: {
                        tab.setText("Drink");
                        tab.setIcon(R.drawable.ic_drink);
                        break;
                    }
                    case 3: {
                        tab.setText("Food");
                        tab.setIcon(R.drawable.ic_food);
                        break;
                    }
                    case 4: {
                        tab.setText("Exercise");
                        tab.setIcon(R.drawable.ic_exercise);
                        break;
                    }
                    case 5: {
                        tab.setText("Meditation");
                        tab.setIcon(R.drawable.ic_resources);
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }
}