package com.example.heiroghliphics_translate_project.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heiroghliphics_translate_project.R;
import com.example.heiroghliphics_translate_project.asyncTasks.updateAsyncTask;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.RoomFactory;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class EditFolderFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private Button editDatePickerBtn;
    ImageView closeBtn;
    CircleImageView editChoosenImage;
    Button editChooseImage;
    EditText editFolderName;
    Button updateFolder;
    private final int REQUEST_CODE = 14;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_folder, container, false);
        editDatePickerBtn=view.findViewById(R.id.edit_date_picker_btn);      //******************Delecration of button in xml with java variable to make setonclicklistener********************
        closeBtn=view.findViewById(R.id.edit_close_iv);
        editChoosenImage=view.findViewById(R.id.edit_selected_image_iv);
        editChooseImage=view.findViewById(R.id.edit_select_image_btn);
        editFolderName=view.findViewById(R.id.edit_foldername_et);
        updateFolder=view.findViewById(R.id.edit_updatefolder_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Addnewfoldermodel folderObjectBeforeUpdate = getFolderFromHomeFragment();

        popUpCalender();
        backToPlacesTranslation();
        selectIamge();
        setUpClickListeners(folderObjectBeforeUpdate);

    }

    private void setUpClickListeners(final Addnewfoldermodel folderObjectBeforeUpdate) {
        updateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Tesssttttt", Toast.LENGTH_SHORT).show();


                String foldername="";
                String date="";
                // ImageView choosenimage=choosenImage.getDrawable();


                if(editFolderName.getText().toString().isEmpty() || editDatePickerBtn.getText().toString().equals("mm-dd-yy") ){
                    Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();
                } else {

                    foldername=editFolderName.getText().toString();
                    date=editDatePickerBtn.getText().toString();

                    folderObjectBeforeUpdate.setFoldername(foldername);
                    folderObjectBeforeUpdate.setDate(date);
                    new updateAsyncTask(RoomFactory.getDatabase(requireContext()).getAddFolder()).execute(folderObjectBeforeUpdate);


                    Navigation.findNavController(view).navigate(R.id.action_editFolderFragment_to_myTranslationsFragment);
                }

            }
        });

    }

    //       ********************* setOnClickListener Function on Button *********************
    private void popUpCalender() {
        editDatePickerBtn.setOnClickListener(new View.OnClickListener() {
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
                                editDatePickerBtn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
                Navigation.findNavController(v).navigate(R.id.action_editFolderFragment_to_myTranslationsFragment);
            }
        });
    }

    // *********************** select image **********************

    private void selectIamge() {
        editChooseImage.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(requireContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();

            Glide.with(getContext()).load(selectedImage).into(editChoosenImage);

        }
    }




    private Addnewfoldermodel getFolderFromHomeFragment() {
        Addnewfoldermodel folderObjectBeforeUpdate = null;

        Bundle arguments = getArguments();
        if(arguments != null){
            folderObjectBeforeUpdate = (Addnewfoldermodel) arguments.getSerializable("folder_object");

            editFolderName.setText(folderObjectBeforeUpdate.getFoldername());
            editDatePickerBtn.setText(folderObjectBeforeUpdate.getDate());
            Glide.with(getContext()).load(folderObjectBeforeUpdate.getImage()).into(editChoosenImage);

        }

        return folderObjectBeforeUpdate;

    }

}