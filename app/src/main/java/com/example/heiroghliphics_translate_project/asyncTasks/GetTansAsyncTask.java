package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.AddFolderDAO;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

import java.util.List;

public class GetTansAsyncTask  extends AsyncTask<Integer, Void, List<Translationtablemodel>> {
    private AddFolderDAO folderDAO;

    public GetTansAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }


    @Override
    protected List<Translationtablemodel> doInBackground(Integer... integers) {
        return folderDAO.gettranslations(integers[0]);
    }
}
