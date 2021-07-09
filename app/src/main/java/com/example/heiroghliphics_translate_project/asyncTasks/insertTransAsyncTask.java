package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.AddFolderDAO;
import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

public class insertTransAsyncTask extends AsyncTask<Translationtablemodel, Void,Void> {
    private AddFolderDAO folderDAO;

    public insertTransAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }
    @Override
    protected Void doInBackground(Translationtablemodel... translationtablemodels) {
        folderDAO.inserttranslation(translationtablemodels[0]);
        return null;
    }
}
