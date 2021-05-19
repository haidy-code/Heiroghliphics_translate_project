package com.example.heiroghliphics_translate_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SplashOne extends AppCompatActivity {
    Button startBtn;
    TextView skipBtn;
    Animation bottomAnim;




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
                finish();
            }
        });


        skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            });
    }
}