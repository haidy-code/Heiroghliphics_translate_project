package com.example.heiroghliphics_translate_project.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.PlacesRVAdapter;
import com.example.heiroghliphics_translate_project.adapters.PlacesTranslationRVAdapter;
import com.example.heiroghliphics_translate_project.models.PlacesModel;
import com.example.heiroghliphics_translate_project.models.PlacesTranslationModel;

import java.util.ArrayList;
import java.util.List;


public class Place_translationFragment extends Fragment {
    private RecyclerView placestranslationRV;
    PlacesTranslationRVAdapter placestranslationRvAdapter;
    private ArrayList<PlacesTranslationModel> placestranslationList = new ArrayList<>();
    private ImageView back_iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_place_translation, container, false);
        placestranslationRV=view.findViewById(R.id.placestranslation_rv);
        back_iv=view.findViewById(R.id.back_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecycleVview();
        addDataToList();
        backclicklistener();
    }


 //    *************************** Fuction on click backIcon go From placeTranslation to myTranslation Fragment **************************

    private void backclicklistener() {
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_place_translationFragment_to_myTranslationsFragment);
            }
        });

    }

    private void addDataToList() {
        PlacesTranslationModel placesTranslationModel=new PlacesTranslationModel("28 May",R.drawable.demotranslation2,R.drawable.demotranslation2,R.drawable.demotranslation2);
        placestranslationList.add(placesTranslationModel);
        placestranslationList.add(placesTranslationModel);
        placestranslationList.add(placesTranslationModel);
        placestranslationList.add(placesTranslationModel);
        placestranslationList.add(placesTranslationModel);
    }

    private void setupRecycleVview() {
        placestranslationRvAdapter = new PlacesTranslationRVAdapter(placestranslationList,requireContext());
        placestranslationRV.setLayoutManager(new GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false));
        placestranslationRV.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        placestranslationRV.setAdapter(placestranslationRvAdapter);
    }
}