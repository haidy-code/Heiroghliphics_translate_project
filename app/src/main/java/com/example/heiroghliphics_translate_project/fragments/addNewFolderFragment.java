package com.example.heiroghliphics_translate_project.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.heiroghliphics_translate_project.MainActivity;
import com.example.heiroghliphics_translate_project.R;

import java.util.Calendar;

public class addNewFolderFragment extends Fragment {


//    ************************** Decleration for DatePickerDialog , Button in xml *************************

    private DatePickerDialog datePickerDialog;
    private Button datePickerBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_folder, container, false);
        datePickerBtn=view.findViewById(R.id.date_picker_btn);      //******************Delecration of button in xml with java variable to make setonclicklistener********************
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//       ********************* setOnClickListener Function on Button *********************
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
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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

}