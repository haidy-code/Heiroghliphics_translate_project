package com.example.heiroghliphics_translate_project.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.models.AllTranslationsModel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

import java.util.List;

public class AllTranslationsRVAdapter extends RecyclerView.Adapter<AllTranslationsRVAdapter.AllTranslationsViewHolder> {
    private List<Translationtablemodel> allTranslationsList;
    private Context context;

    public AllTranslationsRVAdapter(List<Translationtablemodel> allTranslationsList, Context context) {
        this.allTranslationsList = allTranslationsList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllTranslationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.all_translation_rv,parent,false);
        return new AllTranslationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTranslationsViewHolder holder, int position) {


    Translationtablemodel allTranslationsModel = allTranslationsList.get(position);
    Glide.with(context).load(Uri.parse(allTranslationsModel.getCapturedimage())).into(holder.allTranslationImage);



        //Navigate from allTranslation to translationFullDetail Fragment
        holder.allTranslationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("translationlist", allTranslationsList.get(position));
                Navigation.findNavController(v).navigate(R.id.action_allTranslationsFragment_to_translationFullDetailFragment,bundle);


            }
        });



    }

    @Override
    public int getItemCount() {
        return allTranslationsList.size();
    }

    class AllTranslationsViewHolder extends RecyclerView.ViewHolder{
        ImageView allTranslationImage;

        public AllTranslationsViewHolder(@NonNull View itemView) {
            super(itemView);
            allTranslationImage=itemView.findViewById(R.id.all_translation_photo_iv);
        }
    }
}
