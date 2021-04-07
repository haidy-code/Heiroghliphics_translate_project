package com.example.heiroghliphics_translate_project.fragments;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.AllTranslationsRVAdapter;
import com.example.heiroghliphics_translate_project.models.AllTranslationsModel;

import java.util.ArrayList;
import java.util.List;


public class allTranslationsFragment extends Fragment {
    RecyclerView allTranslationsRv;
    ImageButton backToPlaceBtn;
    private AllTranslationsRVAdapter adapter;
    private List<AllTranslationsModel> allTranslationsList = new ArrayList<>();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_translations, container, false);
        allTranslationsRv=view.findViewById(R.id.all_translation_rv);
        backToPlaceBtn=view.findViewById(R.id.back_to_place_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(),4);
        allTranslationsRv.setLayoutManager(layoutManager);
        allTranslationsRv.addItemDecoration(new GridSpacingItemDecoration(4,1,true));
        allTranslationsRv.setItemAnimator(new DefaultItemAnimator());
        adapter = new AllTranslationsRVAdapter(allTranslationsList,requireContext());
        allTranslationsRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        addDataToList();
        BackToPlaceTranslation();
    }
    private void addDataToList(){
        AllTranslationsModel allTranslationsModel=new AllTranslationsModel(R.drawable.demotranslation2);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
        allTranslationsList.add(allTranslationsModel);
    }
    private void BackToPlaceTranslation(){
        backToPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_allTranslationsFragment_to_place_translationFragment);
            }
        });
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}