package com.example.heiroghliphics_translate_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ***********************start of bottom navigation**********************
//
//        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomAppBar);
//        setupWithNavController(bottomNavigationView,navController);

//        disable secand item in menu empty item
//        bottomNavigationView.getMenu().getItem(2).setEnabled(false);


//      *************************end of bottom navigation***************************

    }
}