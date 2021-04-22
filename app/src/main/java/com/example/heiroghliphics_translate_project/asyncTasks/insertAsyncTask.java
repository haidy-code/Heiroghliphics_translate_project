package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.AddFolderDAO;

public class insertAsyncTask extends AsyncTask<Addnewfoldermodel, Void,Void> {
private AddFolderDAO folderDAO;

    public insertAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }

    @Override
    protected Void doInBackground(Addnewfoldermodel... addnewfoldermodels) {
        folderDAO.insertPlace(addnewfoldermodels[0]);
        return null;
    }
}
