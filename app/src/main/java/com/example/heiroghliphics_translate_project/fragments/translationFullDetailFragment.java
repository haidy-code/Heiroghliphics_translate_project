package com.example.heiroghliphics_translate_project.fragments;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_translation_full_detail, container, false);
        backToPlaceTranslation=view.findViewById(R.id.back_to_place_translation_iv);
        actualTranslation=view.findViewById(R.id.actual_translation_tv);
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
                    File sdcard = Environment.getExternalStorageDirectory();
        //Get the text file
                    File file = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/counter.txt");

        //Read text from file
                    StringBuilder text = new StringBuilder();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            Log.i("Test", "text : "+text+" : end");
                            text.append('\n');
                        }
                        br.close();
                    }
                    catch (IOException e) {
                        //You'll need to add proper error handling here
                    }

            //Find the view by its id
//                    TextView tv = (TextView)findViewById(R.id.actualTranslation);

            //Set the text
             actualTranslation.setText(text);

//        StringBuilder sb = new StringBuilder();
//
//
//        try {
//            File sdcard = Environment.getExternalStorageDirectory();
//            File textFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/counter.txt");
//            Log.i("fileeeeee","file error");
//
//
////            File textFile = new File(Environment.getExternalStorageDirectory(),FILENAME);
//            FileInputStream fis = new FileInputStream(textFile);
//            if(fis!=null){
//                InputStreamReader isr = new InputStreamReader(fis);
//                BufferedReader buff = new BufferedReader(isr);
//                String line = null;
//                while((line=buff.readLine())!=null){
//                    sb.append(line+'\n');
//                }
//                fis.close();
//            }
//            actualTranslation.setText(sb);
//                }
//                catch (IOException e) {
//                    //You'll need to add proper error handling here
//                    e.printStackTrace();
//                }
//


    }
}