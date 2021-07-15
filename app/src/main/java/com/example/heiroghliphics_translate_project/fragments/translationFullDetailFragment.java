package com.example.heiroghliphics_translate_project.fragments;
import org.json.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.heiroghliphics_translate_project.MainActivity;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.asyncTasks.GetSymbolAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.GetTansAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.GetplacesAsyncTask;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;
import com.example.heiroghliphics_translate_project.room.Symbolstablemodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;
import static android.provider.Telephony.Mms.Part.FILENAME;


public class translationFullDetailFragment extends Fragment {
    ImageView backToPlaceTranslation;
    TextView actualTranslation;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView image10;
    ImageView image11;
    ImageView takenimage;
    private List<Symbolstablemodel> symbolstablemodels = new ArrayList<>();
    Translationtablemodel translationtablemodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_translation_full_detail, container, false);
        backToPlaceTranslation=view.findViewById(R.id.back_to_place_translation_iv);
        actualTranslation=view.findViewById(R.id.actual_translation_tv);
        image1=view.findViewById(R.id.analyzed_photo_1_tv);
        image2=view.findViewById(R.id.analyzed_photo_2_tv);
        image3=view.findViewById(R.id.analyzed_photo_3_tv);
        image4=view.findViewById(R.id.analyzed_photo_5_tv);
        image5=view.findViewById(R.id.analyzed_photo_6_tv);
        image6=view.findViewById(R.id.analyzed_photo_7_tv);
        image7=view.findViewById(R.id.analyzed_photo_8_tv);
        image8=view.findViewById(R.id.analyzed_photo_9_tv);
        image9=view.findViewById(R.id.analyzed_photo_10_tv);
        image10=view.findViewById(R.id.analyzed_photo_11_tv);
        image11=view.findViewById(R.id.analyzed_photo_12_tv);
        takenimage=view.findViewById(R.id.translatedImage_iv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       translationtablemodel= null;
        //Fragment fragment = new allTranslationsFragment();

        Bundle arguments =   getArguments();
        if(arguments != null){

            translationtablemodel = (Translationtablemodel) arguments.getSerializable("translationlist");
            String x=translationtablemodel.getTranslation();
            x=x.replaceAll("ط","  ");
            x=x.replaceAll("ظ",",");
            actualTranslation.setText(x);
            Glide.with(requireContext()).load(translationtablemodel.getCapturedimage()).into(takenimage);
            try {
                symbolstablemodels.addAll(new GetSymbolAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute( translationtablemodel.getTrans_id()).get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] arr=new String[symbolstablemodels.size()];
            for (int i=0;i<symbolstablemodels.size();i++){
                 arr[i] = symbolstablemodels.get(i).getSymbolpath().toLowerCase();

            }
            int drawableResourceId = requireContext().getResources().getIdentifier(arr[0], "drawable", requireContext().getPackageName());
                Glide.with(requireContext()).load(drawableResourceId).into(image1);
               int drawableResourceId1 = requireContext().getResources().getIdentifier(arr[1], "drawable", requireContext().getPackageName());
                Glide.with(requireContext()).load(drawableResourceId1).into(image2);
                int drawableResourceId2 = requireContext().getResources().getIdentifier(arr[2], "drawable", requireContext().getPackageName());
               Glide.with(requireContext()).load(drawableResourceId2).into(image3);
            int drawableResourceId3 = requireContext().getResources().getIdentifier(arr[3], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId3).into(image4);
            int drawableResourceId4 = requireContext().getResources().getIdentifier(arr[4], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId4).into(image5);
            int drawableResourceId5 = requireContext().getResources().getIdentifier(arr[5], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId5).into(image6);
            int drawableResourceId6 = requireContext().getResources().getIdentifier(arr[6], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId6).into(image7);
            int drawableResourceId7 = requireContext().getResources().getIdentifier(arr[7], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId7).into(image8);
            int drawableResourceId8 = requireContext().getResources().getIdentifier(arr[8], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId8).into(image9);
            int drawableResourceId9 = requireContext().getResources().getIdentifier(arr[9], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId9).into(image10);
            int drawableResourceId10 = requireContext().getResources().getIdentifier(arr[10], "drawable", requireContext().getPackageName());
            Glide.with(requireContext()).load(drawableResourceId10).into(image11);


        }

        backToPlaceTranslation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(requireContext(), translationFullDetailFragment.class);
//
//                Intent intent1 = intent.putExtra("folder_id", translationtablemodel.getWhichfolder());
//                //   intent.putExtra("addressLine", list.get(position).getAddressLine1());
//                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent1);
//                getFragmentManager().popBackStack();



                Navigation.findNavController(view).popBackStack();
            }
        });


//            //JSONObject jsonObject = new JSONObject(str);
//            //return translation
//            Addnewfoldermodel returnedfolderfromdb = null;
//
//            Bundle arguments = getArguments();
//            if(arguments != null){
//                Log.i("hh1","hhhhhhhhhhhhhhhhh");
//                returnedfolderfromdb = (Addnewfoldermodel) arguments.getSerializable("folder_object_fromdb");
//                actualTranslation.setText(returnedfolderfromdb.getTranslation());
//                Log.i("hh",returnedfolderfromdb.getTranslation());
//                String imagename=returnedfolderfromdb.getTranslatedimagename();
//                File sdcard = Environment.getExternalStorageDirectory();
//                File imageFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/Pictures/");
//
//                Uri file=Uri.fromFile(new File(imageFile,imagename));
//
//                if(file.toString() != null && file.toString().length()>0)
//                {
//                    Glide.with(requireContext()).load(file).into(takenimage);
//
//                }else
//                {
//                    Toast.makeText(requireContext(), "Empty URI", Toast.LENGTH_SHORT).show();
//                }
//
//// end of takrn image
//                //reurn sybol list
////
////                String[] arr = returnedfolderfromdb.getArrsymbol();
////
////                int drawableResourceId = requireContext().getResources().getIdentifier(arr[0].toLowerCase(), "drawable", requireContext().getPackageName());
////                Glide.with(requireContext()).load(drawableResourceId).into(image1);
////                int drawableResourceId1 = requireContext().getResources().getIdentifier(arr[1].toLowerCase(), "drawable", requireContext().getPackageName());
////                Glide.with(requireContext()).load(drawableResourceId1).into(image2);
////                int drawableResourceId2 = requireContext().getResources().getIdentifier(arr[2].toLowerCase(), "drawable", requireContext().getPackageName());
////                Glide.with(requireContext()).load(drawableResourceId2).into(image3);
////
////
//           }
//            else{
//                Toast.makeText(requireContext(), "Eror bundle", Toast.LENGTH_LONG).show();
//            }







    }




}




