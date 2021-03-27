package com.example.heiroghliphics_translate_project.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.PlacesRVAdapter;
import com.example.heiroghliphics_translate_project.models.PlacesModel;

import java.util.ArrayList;

public class myTranslationsFragment extends Fragment {
    private RecyclerView placesRv;
    PlacesRVAdapter placesRvAdapter;
    private ArrayList<PlacesModel> placesList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_translations, container, false);
        placesRv=view.findViewById(R.id.places_rv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setuprecyclerview();
        addDataToList();
    }

    private void setuprecyclerview() {


        placesRvAdapter = new PlacesRVAdapter(placesList, requireContext());

        placesRv.setLayoutManager(new GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false));
        placesRv.addItemDecoration(new DividerItemDecoration(requireContext(), 0));

        placesRv.setAdapter(placesRvAdapter);
    }
    private void addDataToList() {
        PlacesModel placesModel=new PlacesModel("Add Another Folder",R.drawable.add);
        placesList.add(placesModel);
        placesList.add(placesModel);
        placesList.add(placesModel);
        placesList.add(placesModel);
    }
}