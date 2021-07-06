package com.example.heiroghliphics_translate_project.fragments;
import org.json.*;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.provider.Telephony.Mms.Part.FILENAME;


public class translationFullDetailFragment extends Fragment {
    ImageView backToPlaceTranslation;
    TextView actualTranslation;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView takenimage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_translation_full_detail, container, false);
        backToPlaceTranslation=view.findViewById(R.id.back_to_place_translation_iv);
        actualTranslation=view.findViewById(R.id.actual_translation_tv);
        image1=view.findViewById(R.id.analyzed_photo_1_tv);
        image2=view.findViewById(R.id.analyzed_photo_2_tv);
        image3=view.findViewById(R.id.analyzed_photo_3_tv);
        takenimage=view.findViewById(R.id.translatedImage_iv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backToPlaceTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_translationFullDetailFragment_to_place_translationFragment);
            }
        });
        String str=readfromfile();
        try {
            JSONObject jsonObject = new JSONObject(str);
            actualTranslation.setText(jsonObject.get("translation").toString());
            String imagename=jsonObject.get("imageName").toString();

            Uri image=Uri.parse(readimage(imagename));


            Glide.with(requireContext()).load(image).into(takenimage);

            JSONArray arrJson = jsonObject.getJSONArray("symbolsList");
            String[] arr = new String[arrJson.length()];
            for(int i = 0; i < arrJson.length(); i++) {
                arr[i] = arrJson.getString(i);

            }
            int drawableResourceId = requireContext().getResources().getIdentifier(arr[0].toLowerCase(), "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId).into(image1);
            int drawableResourceId1 = requireContext().getResources().getIdentifier(arr[1].toLowerCase(), "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId1).into(image2);
            int drawableResourceId2 = requireContext().getResources().getIdentifier(arr[2].toLowerCase(), "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId2).into(image3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("haidy",str);
    }
    private String readimage(String imagename) {

        String ret="";
        StringBuilder sb = new StringBuilder();


        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File imageFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/Pictures/"+imagename);
//            File textFile = new File(Environment.getExternalStorageDirectory(),FILENAME);
            FileInputStream fis = new FileInputStream(imageFile);

            if(fis!=null){
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while((line=buff.readLine())!=null){
                    sb.append(line+'\n');
                }
                ret=sb.toString();
                fis.close();
            }
            // actualTranslation.setText(sb);
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
        }

        Log.i("manon",ret);
        return ret;
    }

    private String readfromfile() {

        String ret="";
        StringBuilder sb = new StringBuilder();


        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File textFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/data.json");
            Log.i("fileeeeee","file path");


//            File textFile = new File(Environment.getExternalStorageDirectory(),FILENAME);
            FileInputStream fis = new FileInputStream(textFile);
            if(fis!=null){
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while((line=buff.readLine())!=null){
                    sb.append(line+'\n');
                }
                ret=sb.toString();
                fis.close();
            }
           // actualTranslation.setText(sb);
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
        }
        return ret;
    }


}




