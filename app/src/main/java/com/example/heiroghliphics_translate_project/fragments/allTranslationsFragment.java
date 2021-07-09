package com.example.heiroghliphics_translate_project.fragments;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.adapters.AllTranslationsRVAdapter;
import com.example.heiroghliphics_translate_project.asyncTasks.GetTansAsyncTask;
import com.example.heiroghliphics_translate_project.models.AllTranslationsModel;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//bokra bundle ,get id() ,han5aly transtablew alltranslation model model wa7d
public class allTranslationsFragment extends Fragment {
    RecyclerView allTranslationsRv;
    ImageButton backToPlaceBtn;
    private AllTranslationsRVAdapter adapter;


    private List<Translationtablemodel> TranslationsList = new ArrayList<>();
    ImageView imageView;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_translations, container, false);
        allTranslationsRv=view.findViewById(R.id.all_translation_rv);
        backToPlaceBtn=view.findViewById(R.id.back_to_place_btn);
        imageView=view.findViewById(R.id.all_translation_photo_iv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();


       Addnewfoldermodel returnedfolder = null;
        //Fragment fragment = new allTranslationsFragment();

        Bundle arguments =   getArguments();
        if(arguments != null&&!arguments.isEmpty()){

            returnedfolder = (Addnewfoldermodel) arguments.getSerializable("folder_object_to alltransfrag");
            arguments.clear();
//            Toast.makeText(requireContext(), (""+returnedfolder.getFolderid()), Toast.LENGTH_SHORT).show();

            //return translation
            try {
                TranslationsList.addAll(new GetTansAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute( returnedfolder.getFolderid()).get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<TranslationsList.size();i++){
          //     TranslationsList.get(i).setWhichfolder(returnedfolder.getFolderid()+"");
                String imagename=TranslationsList.get(i).getCapturedimage();
                File sdcard = Environment.getExternalStorageDirectory();
                File imageFile = new File(sdcard,"/Android/data/com.example.heiroghliphics_translate_project/files/Pictures/");

                Uri file=Uri.fromFile(new File(imageFile,imagename));

                if(file.toString() != null && file.toString().length()>0)
                {
                    // Glide.with(requireContext()).load(file).into(takenimage);

                        Translationtablemodel translationtablemodel=TranslationsList.get(i);
                        translationtablemodel.setCapturedimage(file.toString());
                  //  Glide.with(requireContext()).load(file).into(imageView);


                        //   Glide.with(requireContext()).load(file).into(translationtablemodel.);
//                    AllTranslationsModel allTranslationsModel=new AllTranslationsModel();
//                    allTranslationsModel.setTranslatedImage(Integer.parseInt(translationtablemodel.getCapturedimage()));



                }else
                {
                    Toast.makeText(requireContext(), "Empty URI", Toast.LENGTH_SHORT).show();
                }
            }





// end of takrn image
            //reurn sybol list
//
//                String[] arr = returnedfolderfromdb.getArrsymbol();
//
//                int drawableResourceId = requireContext().getResources().getIdentifier(arr[0].toLowerCase(), "drawable", requireContext().getPackageName());
//                Glide.with(requireContext()).load(drawableResourceId).into(image1);
//                int drawableResourceId1 = requireContext().getResources().getIdentifier(arr[1].toLowerCase(), "drawable", requireContext().getPackageName());
//                Glide.with(requireContext()).load(drawableResourceId1).into(image2);
//                int drawableResourceId2 = requireContext().getResources().getIdentifier(arr[2].toLowerCase(), "drawable", requireContext().getPackageName());
//                Glide.with(requireContext()).load(drawableResourceId2).into(image3);
//
//
        }
        else{
            Toast.makeText(requireContext(), "Eror bundle", Toast.LENGTH_LONG).show();
        }


    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(),4);
        allTranslationsRv.setLayoutManager(layoutManager);
        allTranslationsRv.addItemDecoration(new GridSpacingItemDecoration(4,1,true));
        allTranslationsRv.setItemAnimator(new DefaultItemAnimator());
        adapter = new AllTranslationsRVAdapter(TranslationsList,requireContext());
        allTranslationsRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    //    addDataToList();
        BackToPlaceTranslation();
    }

    private void BackToPlaceTranslation(){
        backToPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_allTranslationsFragment_to_myTranslationsFragment);
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