package com.example.heiroghliphics_translate_project.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.PlacesRVAdapter;
import com.example.heiroghliphics_translate_project.asyncTasks.GetplacesAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.deleteAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.insertAsyncTask;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;
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
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class myTranslationsFragment extends Fragment {
    private RecyclerView placesRv;
    PlacesRVAdapter placesRvAdapter;
    private ArrayList<Addnewfoldermodel> foldersList = new ArrayList<>();
    private ArrayList<Translationtablemodel> transList = new ArrayList<>();
    TextView foldername;
    TextView transno;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_translations, container, false);
        placesRv=view.findViewById(R.id.translationPlaces_rv);
        foldername=view.findViewById(R.id.translationPlace_tv);
        transno=view.findViewById(R.id.trans_no_tv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAllfoldersFromDB();
        setupRecycleVview();
      //  transList = null;
        //Fragment fragment = new allTranslationsFragment();
//
//        Bundle arguments =   getArguments();
//        if(arguments != null){
//
//            transList = (ArrayList<Translationtablemodel>) arguments.getSerializable("translationslist");}
//        for (int i=0;i<foldersList.size();i++){
//        if(foldersList.get(i).getFoldername().equals("Add Another Folder")){
//            transno.setText("");
//
//
//
//        }
//        else {
//
//
//            // transno.setVisibility(View.VISIBLE);
//             transno.setText("bbbbb");
//
//
//
//
//
//    }
//        }
//        transno.setText("hhhhh");

        }

    private void getAllfoldersFromDB() {
        foldersList.clear();
        try {
//            Uri addimage=Uri.parse("android.resource://com.example.heiroghliphics_translate_project/drawable/add");
//            String addpath=addimage.toString();
            Addnewfoldermodel placesModel=new Addnewfoldermodel("Add Another Folder",R.drawable.add);
            foldersList.add(placesModel);
            foldersList.addAll(new GetplacesAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute().get());


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setupRecycleVview() {
//        Collections.reverse(foldersList);
        placesRvAdapter = new PlacesRVAdapter(foldersList, requireContext(), new PlacesRVAdapter.OnPlaceClickListener() {
            @Override
            public void onPlaceClick(View view, int position) {


                if(foldersList.get(position).getFoldername().equals("Add Another Folder")){
                   // transno.setVisibility(View.INVISIBLE);


                   Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_addNewFolderFragment);
                }
                else {


                  //  transno.setVisibility(View.VISIBLE);

                setUpEditOrDeletorvieweDialog(view , position);}



            }
        });
        placesRv.setLayoutManager(new GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false));
        placesRv.addItemDecoration(new DividerItemDecoration(requireContext(), 0));//orientation zero brcause i deleted line seperating each item in recycler view
        placesRv.setAdapter(placesRvAdapter);
    }

    private void setUpEditOrDeletorvieweDialog(final View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Do you want to edit or delete or view this note?");
        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                deletefolder(position);
                getAllfoldersFromDB();
            }
        });
        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Addnewfoldermodel folder = foldersList.get(position);
//                // to send data to EditFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("folder_object", folder);
                Navigation.findNavController(view).navigate(R.id.action_myTranslationsFragment_to_editFolderFragment, bundle);



            }
        });

        dialog.show();

    }

    private void deletefolder(int which) {
        Addnewfoldermodel folder=foldersList.get(which);
        new deleteAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute(folder);
        placesRvAdapter.notifyDataSetChanged();
    }





}