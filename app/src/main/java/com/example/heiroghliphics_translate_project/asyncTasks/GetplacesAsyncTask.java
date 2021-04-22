package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.Addnewfoldermodel;
import com.example.heiroghliphics_translate_project.room.AddFolderDAO;

import java.util.List;

public class GetplacesAsyncTask extends AsyncTask<Void, Void, List<Addnewfoldermodel>> {
    private AddFolderDAO folderDAO;

    public GetplacesAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }

    @Override
    protected List<Addnewfoldermodel> doInBackground(Void... voids) {
        return folderDAO.getAllPlaces();
    }
}
