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

    public PlacesRVAdapter(List<PlacesModel> placesList, Context context) {
        this.placesList = placesList;
        this.context = context;
    }

    @NonNull
    @Override
    public Placesviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_rv_item, parent, false);
        return new Placesviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Placesviewholder holder, int position) {
        PlacesModel placesModel=placesList.get(position);;
        holder.nametv.setText(placesModel.getName());
        Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.imageiv);
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    class Placesviewholder extends RecyclerView.ViewHolder{
        TextView nametv;
        ImageView imageiv;
        public Placesviewholder(@NonNull View itemView) {
            super(itemView);
            nametv=itemView.findViewById(R.id.place_tv);
            imageiv=itemView.findViewById(R.id.place_iv);
        }
    }
}
