package com.example.heiroghliphics_translate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ***********************start of bottom navigation**********************

//        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomAppBar);
//        NavigationUI.setupWithNavController(bottomNavigationView,navController);

//        disable secand item in menu empty item
//        bottomNavigationView.getMenu().getItem(2).setEnabled(false);


//      *************************end of bottom navigation***************************

    }
}