package com.example.heiroghliphics_translate_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class SplashOne extends AppCompatActivity {
    Button startBtn;
    TextView skipBtn;
    Animation bottomAnim;
    public FlutterEngine flutterEngine;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_one);
        startBtn=findViewById(R.id.start_btn);
        skipBtn=findViewById(R.id.skip);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);


        skipBtn.setAnimation(bottomAnim);



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartSplash.class);
                startActivity(intent);
            }
        });


        skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TDOO: go to file system
                    // Go to file system
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    // flutter
                    cashingFlutterEngine();
                }
            });
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