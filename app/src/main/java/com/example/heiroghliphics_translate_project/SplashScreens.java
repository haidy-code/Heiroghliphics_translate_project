package com.example.heiroghliphics_translate_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreens extends AppCompatActivity {
    ImageView iconApp;
   TextView heiroText;
   TextView glyphicText;

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
                    finish();
                }
                else {

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIMER);

    }
}