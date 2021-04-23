package com.example.heiroghliphics_translate_project.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public  class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.Placesviewholder> {

    private List<Addnewfoldermodel> foldersList;
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
        Addnewfoldermodel placesModel= foldersList.get(position);;
        holder.translationNametv.setText(placesModel.getFoldername());
        Glide.with(context).load(placesModel.getImage()).into(holder.folderTranslationImageiv);

        //navigate from my translation to place translation
        holder.arrow_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_place_translationFragment);
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
}
