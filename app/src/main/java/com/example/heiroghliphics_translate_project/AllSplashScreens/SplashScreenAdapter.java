package com.example.heiroghliphics_translate_project.AllSplashScreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.heiroghliphics_translate_project.R;

public class SplashScreenAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;

    public SplashScreenAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.ic_one,
            R.drawable.ic_two,
            R.drawable.ic__,

    };

    int headings[]={
            R.string.first_slide_title,
            R.string.secand_slide_title,
            R.string.third_slide_title,
    };

    int descriptions[]={
            R.string.first_slide_desc,
            R.string.secand_slide_desc,
            R.string.third_slide_desc,
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.splash_screens_layout,container,false);

        ImageView imageSplash = view.findViewById(R.id.image_splash_iv);
        TextView titleSplash = view.findViewById(R.id.title_splash_tv);
        TextView descSplash = view.findViewById(R.id.desc_splash_tv);


         imageSplash.setImageResource(images[position]);
         titleSplash.setText(headings[position]);
         descSplash.setText(descriptions[position]);

         container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
