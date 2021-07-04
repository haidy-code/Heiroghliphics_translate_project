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

import com.example.heiroghliphics_translate_project.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_translation_full_detail, container, false);
        backToPlaceTranslation=view.findViewById(R.id.back_to_place_translation_iv);
        actualTranslation=view.findViewById(R.id.actual_translation_tv);
        image1=view.findViewById(R.id.analyzed_photo_1_tv);
        image2=view.findViewById(R.id.analyzed_photo_2_tv);
        image3=view.findViewById(R.id.analyzed_photo_3_tv);
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

            //Find the directory for the SD Card using the API
            //*Don't* hardcode "/sdcard"
//                    File sdcard = Environment.getExternalStorageDirectory();
//        //Get the text file
//                    File file = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/counter.txt");
//
//        //Read text from file
//                    StringBuilder text = new StringBuilder();
//
//                    try {
//                        BufferedReader br = new BufferedReader(new FileReader(file));
//                        String line;
//
//                        while ((line = br.readLine()) != null) {
//                            text.append(line);
//                            Log.i("Test", "text : "+text+" : end");
//                            text.append('\n');
//                        }
//                        br.close();
//                    }
//                    catch (IOException e) {
//                        //You'll need to add proper error handling here
//                    }

            //Find the view by its id
//                    TextView tv = (TextView)findViewById(R.id.actualTranslation);

            //Set the text
//             actualTranslation.setText(text);
               String str=readfromfile();
     //   actualTranslation.setText(str);
            Gson g=new Gson();
        JsonObject jsonObject = (new JsonParser()).parse(str).getAsJsonObject();
        Log.i("haidy3",jsonObject.getAsString());
        actualTranslation.setText(jsonObject.getAsString());
//              translationFullDetailFragment parentobject=g.fromJson(str,translationFullDetailFragment.class);
             //  String translationList=parentobject.getString();
        //JSONObject parentObject=null;
//        try {
//            StringBuffer finalbufferdata=new StringBuffer();

//            String translationList=parentObject.getString("translationList");
//            finalbufferdata.append(translationList);
//            actualTranslation.setText(translationList);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        Log.i("haidy",str);


    }

    private String readfromfile() {

        String ret="";
        StringBuilder sb = new StringBuilder();


        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File textFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/data.json");
            Log.i("fileeeeee","file error");


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




