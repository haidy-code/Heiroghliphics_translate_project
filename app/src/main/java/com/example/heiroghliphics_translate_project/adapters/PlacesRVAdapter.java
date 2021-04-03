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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.Placesviewholder> {

    private List<PlacesModel> placesList;
    private Context context;
    private OnPlaceClickListener onPlaceClickListener;

    public interface OnPlaceClickListener {
        void onPlaceClick(View view, int position);
    }

    public PlacesRVAdapter(List<PlacesModel> placesList, Context context, OnPlaceClickListener onPlaceClickListener) {
        this.placesList = placesList;
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
        PlacesModel placesModel=placesList.get(position);;
        holder.translationNametv.setText(placesModel.getName());
        Glide.with(context).load(placesModel.getImage()).into(holder.folderTranslationImageiv);
        holder.arrow_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
onPlaceClickListener.onPlaceClick(view,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
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
