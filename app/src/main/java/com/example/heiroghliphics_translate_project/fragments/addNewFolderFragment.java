package com.example.heiroghliphics_translate_project.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.MainActivity;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.asyncTasks.GetplacesAsyncTask;
import com.example.heiroghliphics_translate_project.asyncTasks.insertAsyncTask;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class addNewFolderFragment extends Fragment {


//    ************************** Decleration for DatePickerDialog , Button in xml , choose imagefrom library*************************

    private DatePickerDialog datePickerDialog;
    private Button datePickerBtn;
    ImageView closeBtn;
    CircleImageView choosenImage;
    Button chooseImage;
    EditText folderName;
    Button addFolder;
    private final int REQUEST_CODE = 14;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_folder, container, false);
        datePickerBtn=view.findViewById(R.id.date_picker_btn);      //******************Delecration of button in xml with java variable to make setonclicklistener********************
        closeBtn=view.findViewById(R.id.close_iv);
        choosenImage=view.findViewById(R.id.selected_image_iv);
        chooseImage=view.findViewById(R.id.select_image_btn);
        folderName=view.findViewById(R.id.foldername_et);
        addFolder=view.findViewById(R.id.addfolder_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        popUpCalender();
        backToPlacesTranslation();
        selectIamge();
        setUpClickListeners();
    }
// checks for folder name ,date , image empty or not
    private void setUpClickListeners() {
        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foldername=folderName.getText().toString();
                String date=datePickerBtn.getText().toString();
               // ImageView choosenimage=choosenImage.getDrawable();



                if(foldername.isEmpty() || date.equals("mm-dd-yy") ){
                    Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();
                } else {

                    insertToRoom(foldername , date);
                    Toast.makeText(requireContext(), date, Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(view).navigate(R.id.action_addNewFolderFragment_to_myTranslationsFragment);
                }

            }
        });
    }
    private void insertToRoom(String foldername , String date) {

        new insertAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute(new Addnewfoldermodel(foldername,date));


    }
    private void selectIamge() {
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            Uri selectedImage = data.getData();

            Glide.with(getContext()).load(selectedImage).into(choosenImage);

        }
    }

    //       ********************* setOnClickListener Function on Button *********************
    private void popUpCalender() {
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               *************************** New object from calender && decleration of calender variable ******************
                Calendar cal =Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

//              ***********************  New object from DatePickerDialog
//              *********************** take:
//              ***********************  1- Activity
//              ***********************  2-Teme of calender
//              ***********************  3-function when click of date on calender(put function set date)
//              ***********************  4-year,date,month

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity() ,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                datePickerBtn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        },year,month,day);

                dialog.show(); // **************to show calender when button clicked*******************

            }
        });
    }

//    ************************Navigate from addNewFolder to My placesTranslation*********************
    private void backToPlacesTranslation(){
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addNewFolderFragment_to_myTranslationsFragment);
            }
        });
    }

}