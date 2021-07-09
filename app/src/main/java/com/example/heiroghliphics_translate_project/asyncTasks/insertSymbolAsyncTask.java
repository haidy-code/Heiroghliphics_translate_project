package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.AddFolderDAO;
import com.example.heiroghliphics_translate_project.room.Symbolstablemodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

public class insertSymbolAsyncTask extends AsyncTask<Symbolstablemodel, Void,Void> {
    private AddFolderDAO folderDAO;

    public insertSymbolAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }
    @Override
    protected Void doInBackground(Symbolstablemodel... symbolstablemodels) {
        folderDAO.insertsymbol(symbolstablemodels[0]);
        return null;
    }
}
