package com.example.heiroghliphics_translate_project.asyncTasks;

import android.os.AsyncTask;

import com.example.heiroghliphics_translate_project.room.AddFolderDAO;
import com.example.heiroghliphics_translate_project.room.Symbolstablemodel;
import com.example.heiroghliphics_translate_project.room.Translationtablemodel;

import java.util.List;

public class GetSymbolAsyncTask  extends AsyncTask<Integer, Void, List<Symbolstablemodel>> {
    private AddFolderDAO folderDAO;

    public GetSymbolAsyncTask(AddFolderDAO folderDAO) {
        this.folderDAO = folderDAO;
    }
    @Override
    protected List<Symbolstablemodel> doInBackground(Integer... integers) {
        return folderDAO.getsymbols(integers[0]);
    }
}
