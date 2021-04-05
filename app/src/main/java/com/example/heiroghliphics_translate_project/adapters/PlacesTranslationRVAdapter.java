package com.example.heiroghliphics_translate_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.models.PlacesModel;
import com.example.heiroghliphics_translate_project.models.PlacesTranslationModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesTranslationRVAdapter extends RecyclerView.Adapter<PlacesTranslationRVAdapter.PlacesTranslationViewholder> {
    private List<PlacesTranslationModel> placestranslationList;
    private Context context;

    public PlacesTranslationRVAdapter(List<PlacesTranslationModel> placestranslationList, Context context) {
        this.placestranslationList = placestranslationList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlacesTranslationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_translation_rv_item, parent, false);
        return new PlacesTranslationViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesTranslationViewholder holder, int position) {
        PlacesTranslationModel placesTranslationModel=placestranslationList.get(position);

        holder.datetv.setText(placesTranslationModel.getDate());
        Glide.with(context).load(placesTranslationModel.getImage1()).into(holder.photo1Imageiv);
        Glide.with(context).load(placesTranslationModel.getImage2()).into(holder.photo2Imageiv);
        Glide.with(context).load(placesTranslationModel.getImage3()).into(holder.photo3Imageiv);

    }

    @Override
    public int getItemCount() {
        return placestranslationList.size();
    }

    class PlacesTranslationViewholder extends RecyclerView.ViewHolder{
        TextView datetv;
        ImageView photo1Imageiv;
        ImageView photo2Imageiv;
        ImageView photo3Imageiv;


        public PlacesTranslationViewholder(@NonNull View itemView) {
            super(itemView);
            datetv=itemView.findViewById(R.id.date_tv);
            photo1Imageiv=itemView.findViewById(R.id.photo_1_tv_places_translation);
            photo2Imageiv=itemView.findViewById(R.id.photo_2_tv_places_translation);
            photo3Imageiv=itemView.findViewById(R.id.photo_3_tv_places_translation);

            //    *************************** Fuction on click translated Image go to translationFullDetail Fragment  **************************
            photo1Imageiv.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View v) {
                  Navigation.findNavController(v).navigate(R.id.action_place_translationFragment_to_translationFullDetailFragment); }
                });
        }
    }
}
