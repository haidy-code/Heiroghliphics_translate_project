package com.example.heiroghliphics_translate_project.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.asyncTasks.GetTansAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.insertSymbolAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.insertTransAsyncTask;
import com.example.heiroghliphics_translate_project.fragments.allTranslationsFragment;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;
import com.example.heiroghliphics_translate_project.room.Symbolstablemodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public  class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.Placesviewholder> {

    private List<Addnewfoldermodel> foldersList;
    private List<Translationtablemodel> translationList=new ArrayList<>();
    private List<Symbolstablemodel> SymbolsList;
    private Symbolstablemodel symbolstablemodel;
    private Context context;
    private OnPlaceClickListener onPlaceClickListener;

    public interface OnPlaceClickListener {
        void onPlaceClick(View view, int position);
    }

    public PlacesRVAdapter(List<Addnewfoldermodel> placesList, Context context, OnPlaceClickListener onPlaceClickListener) {
        this.foldersList = placesList;
        this.context = context;
        this.onPlaceClickListener = onPlaceClickListener;
    }

    @NonNull
    @Override
    public Placesviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_rv_item, parent, false);
        return new Placesviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Placesviewholder holder, int position) {
        Addnewfoldermodel placesModel= foldersList.get(position);
        holder.translationNametv.setText(placesModel.getFoldername());
       // Log.d("Omar",placesModel.getImage());

        if(placesModel.getImage() != null){
            Uri imageUri = Uri.parse( placesModel.getImage());
            if (placesModel.getFoldername().equals("Add Another Folder")){
                Glide.with(context).load(R.drawable.add).into(holder.folderTranslationImageiv);
            }
            if(imageUri == null&&placesModel.getIcons()==0){
                if (placesModel.getFoldername().equals("Add Another Folder")){
                    Glide.with(context).load(R.drawable.add).into(holder.folderTranslationImageiv);
                }
                Glide.with(context).load(R.drawable.ic_place_holder).into(holder.folderTranslationImageiv);

            } else {
                if (placesModel.getFoldername().equals("Add Another Folder")){
                    Glide.with(context).load(R.drawable.add).into(holder.folderTranslationImageiv);}
                Glide.with(context).load(imageUri).into(holder.folderTranslationImageiv);

            }

        }
        //ana ally mgaraba al7ta dh
        else if (placesModel.getImage()==null&&placesModel.getIcons()==0){
            if (placesModel.getFoldername().equals("Add Another Folder")){
                Glide.with(context).load(R.drawable.add).into(holder.folderTranslationImageiv);}
            Glide.with(context).load(R.drawable.ic_place_holder).into(holder.folderTranslationImageiv);
        }





        //navigate from my translation to place translation
        holder.arrow_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(foldersList.get(position).getFoldername().equals("Add Another Folder")){
                    Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_addNewFolderFragment);

                }
                else{

                    try {
                        String str=readfromfile();
                        //delete the file
                        deletefile();
                        Log.i("delete","delete");
                        JSONObject jsonObject = new JSONObject(str);
                        //return translation
                        String translation=jsonObject.get("translation").toString();

                        String imagename=jsonObject.get("imageName").toString();
                        Translationtablemodel translationtablemodel=new Translationtablemodel();
                        translationtablemodel.setTranslation(translation);
                        translationtablemodel.setCapturedimage(imagename);
                        translationtablemodel.setWhichfolder(foldersList.get(position).getFolderid());
                        new insertTransAsyncTask(RoomFactory.getDatabase(context).getAddFolder()).execute(translationtablemodel);
                        //reurn sybol list
                        JSONArray arrJson = jsonObject.getJSONArray("symbolsList");
                        String[] arr = new String[arrJson.length()];
                        translationList.addAll(new GetTansAsyncTask(RoomFactory.getDatabase(context).getAddFolder()).execute( foldersList.get(position).getFolderid()).get());
                        for(int i = 0; i < arrJson.length(); i++) {
                            arr[i] = arrJson.getString(i);
                             symbolstablemodel=new Symbolstablemodel();
                            symbolstablemodel.setSymbolpath(arr[i]);
                            for (int j=0;j<translationList.size();j++){
                            translationtablemodel=translationList.get(j);
                            symbolstablemodel.setWhichtranslation(translationtablemodel.getTrans_id());}


                            //Log.i("hh1",translationtablemodel.getTrans_id()+"");
                            new insertSymbolAsyncTask(RoomFactory.getDatabase(context).getAddFolder()).execute(symbolstablemodel);
                        }


                        Bundle bundle = new Bundle();
                        bundle.putSerializable("folder_object_to alltransfrag", foldersList.get(position));
                        Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_allTranslationsFragment,bundle);




//                        foldersList.get(position).setTranslation(translation);
//                        foldersList.get(position).setTranslatedimagename(imagename);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("folder_object_fromdb", foldersList.get(position));
                        //return taken image to translation full detail



//                    foldersList.get(position).setArrsymbol(arr);

                      //  new insertAsyncTask(RoomFactory.getDatabase(context).getAddFolder()).execute(foldersList.get(position));
//                        Addnewfoldermodel returnedfolderfromdb=new GetplaceAsyncTask(RoomFactory.getDatabase(context).getAddFolder()).execute(foldersList.get(position).getFoldername()).get();



}
                    catch (JSONException e) {
                        Log.i("h1","11");
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("folder_object_to alltransfrag", foldersList.get(position));
                        Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_allTranslationsFragment,bundle);
                      e.printStackTrace();
                  }
                    catch (InterruptedException e) {
                        Log.i("h2","22");
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        Log.i("h3","33");
                        e.printStackTrace();
                    }


                }
            }
        });


        //          navigate from myTranslationfolders Fragment to addNewFolder Fragment


//        holder.folderTranslationImageiv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri addimage=Uri.parse("android.resource://com.example.heiroghliphics_translate_project/"+R.drawable.add);
//                String addpath=addimage.toString();
//                Uri placeoraddimage=Uri.parse("android.resource://com.example.heiroghliphics_translate_project/"+holder.folderTranslationImageiv.getDrawable());
//                String placeimg=placeoraddimage.toString();
//                if (placeimg .equals(addpath) ){
//                    Navigation.findNavController(v).navigate(R.id.action_myTranslationsFragment_to_addNewFolderFragment);
//                }
//
//                else {
//                  // Toast.makeText(context, placeimg, Toast.LENGTH_LONG).show();
//                    Toast.makeText(context, addpath, Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

        // when clicking on place row
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
onPlaceClickListener.onPlaceClick(view,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foldersList.size();
    }

    class Placesviewholder extends RecyclerView.ViewHolder{
        TextView translationNametv;
        ImageView folderTranslationImageiv;
        ImageView arrow_iv;
        public Placesviewholder(@NonNull View itemView) {
            super(itemView);
            translationNametv=itemView.findViewById(R.id.translationPlace_tv);
            folderTranslationImageiv=itemView.findViewById(R.id.folderTranslationImage_iv);
            arrow_iv=itemView.findViewById(R.id.arrow_iv);

        }
    }
    private void deletefile(){
        File sdcard = Environment.getExternalStorageDirectory();
        File textFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/data.json");
        textFile.delete();
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
