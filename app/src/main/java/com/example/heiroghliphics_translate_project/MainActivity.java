package com.example.heiroghliphics_translate_project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class MainActivity extends AppCompatActivity {
    public FlutterEngine flutterEngine;

    FloatingActionButton camera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera=findViewById(R.id.camera);

//        ********start of bottom navigation*******

        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

//        disable camera item which defined in menu as a blanked for desgin
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);

//      ********end of bottom navigation**********

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashingFlutterEngine();

            }
        });

    }
    public void cashingFlutterEngine(){

        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(getApplicationContext());
        flutterEngine.getNavigationChannel().setInitialRoute("/CaptureingScreen");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);
        startActivity(
                FlutterActivity
                        .withCachedEngine("my_engine_id")
                        .build(getApplicationContext())

        );

//        finish();


    }

}