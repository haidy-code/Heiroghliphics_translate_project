package com.example.heiroghliphics_translate_project.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.PlacesRVAdapter;
import com.example.heiroghliphics_translate_project.asyncTasks.GetplacesAsyncTask;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class myTranslationsFragment extends Fragment {
    private RecyclerView placesRv;
    PlacesRVAdapter placesRvAdapter;
    private ArrayList<Addnewfoldermodel> foldersList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_translations, container, false);
        placesRv=view.findViewById(R.id.translationPlaces_rv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAllfoldersFromDB();
        setupRecycleVview();
       // addDataToList();

        }

    private void getAllfoldersFromDB() {
        foldersList.clear();
        try {
            foldersList.addAll(new GetplacesAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute().get());
            Addnewfoldermodel placesModel=new Addnewfoldermodel("Add Another Folder",R.drawable.add);
            foldersList.add(placesModel);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setupRecycleVview() {
        placesRvAdapter = new PlacesRVAdapter(foldersList, requireContext(), new PlacesRVAdapter.OnPlaceClickListener() {
            @Override
            public void onPlaceClick(View view, int position) {
                Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_addNewFolderFragment);
                //Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();


            }
        });
        placesRv.setLayoutManager(new GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false));
        placesRv.addItemDecoration(new DividerItemDecoration(requireContext(), 0));//orientation zero brcause i deleted line seperating each item in recycler view
        placesRv.setAdapter(placesRvAdapter);
    }
//    private void addDataToList() {
//       foldersList.clear();
//        Addnewfoldermodel placesModel=new Addnewfoldermodel("Add Another Folder",R.drawable.add);
//        foldersList.add(placesModel);
//
//    }
}