package com.example.heiroghliphics_translate_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiroghliphics_translate_project.AllSplashScreens.cashingFlutterEngine;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;


public class SplashScreens extends cashingFlutterEngine {
    ImageView iconApp;
   TextView heiroText;
   TextView glyphicText;
    public FlutterEngine flutterEngine;



    //Animation decleration which created in anim directory
    Animation sideAnim , bottomAnim;

    // splash timer
    private static int SPLASH_TIMER = 5000;

    SharedPreferences startSplash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screens);
        iconApp=findViewById(R.id.splash_icon_iv);
        heiroText=findViewById(R.id.heiro_tv);
        glyphicText=findViewById(R.id.glyphic_tv);
        //Animation
        sideAnim= AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        //set Animation of elements
        iconApp.setAnimation(sideAnim);
        heiroText.setAnimation(bottomAnim);
        glyphicText.setAnimation(bottomAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startSplash=getSharedPreferences("startSplasScreen" , MODE_PRIVATE);
                boolean isFirstTime=startSplash.getBoolean("firstTime" , true);

                if(isFirstTime){
                    SharedPreferences.Editor editor = startSplash.edit();
                    editor.putBoolean("firstTime" , false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), SplashOne.class);
                    startActivity(intent);
                }
                else {

                    // Go to file system
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    // flutter
                    cashingFlutterEngine();

                }

            }
        },SPLASH_TIMER);

    }


    public void cashingFlutterEngine(){

        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(getApplicationContext());

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

        finish();


    }


}
