package com.example.heiroghliphics_translate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heiroghliphics_translate_project.AllSplashScreens.SplashScreenAdapter;

public class StartSplash extends AppCompatActivity {
    ViewPager viewPager;
    SplashScreenAdapter splashScreenAdapter;
    LinearLayout dotsLayout;
    TextView[] dots;
    Button startBtn;
    TextView skipBtn;
    TextView next;
    TextView back;
    int currentPosition;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide al status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_splash);


        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        startBtn=findViewById(R.id.start_activity_btn);
        next=findViewById(R.id.next);
        back=findViewById(R.id.back);
        skipBtn=findViewById(R.id.skip);
        splashScreenAdapter = new SplashScreenAdapter(this);
        viewPager.setAdapter(splashScreenAdapter);
        addDots(0);
        animation= AnimationUtils.loadAnimation(StartSplash.this , R.anim.side_anim);

        viewPager.addOnPageChangeListener(changeListener);
        skip(viewPager);
        next(viewPager);
        back(viewPager);
        startActivity(viewPager);
    }

    public void skip(View view){
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
    public void startActivity (View view){
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
    public void next(View view){
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               viewPager.setCurrentItem(currentPosition + 1);
           }
       });

    }

    public void back(View view){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPosition - 1);

            }
        });

    }

    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.lightGray));
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.yellow));

        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPosition=position;
            addDots(position);
             if(position==0){
                back.setVisibility(View.INVISIBLE);
                startBtn.setVisibility(View.INVISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                skipBtn.setVisibility(View.VISIBLE);

            }
            else if (position==1 ){
                startBtn.setVisibility(View.INVISIBLE);
                dotsLayout.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                skipBtn.setVisibility(View.VISIBLE);
            }
            else {
                startBtn.setVisibility(View.VISIBLE);
                animation= AnimationUtils.loadAnimation(StartSplash.this , R.anim.bottom_anim);
                startBtn.setAnimation(animation);
                dotsLayout.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                skipBtn.setVisibility(View.INVISIBLE);

            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}