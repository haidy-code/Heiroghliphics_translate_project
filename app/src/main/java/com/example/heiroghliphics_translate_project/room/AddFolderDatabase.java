package com.example.heiroghliphics_translate_project.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Addnewfoldermodel.class,Translationtablemodel.class,Symbolstablemodel.class}, version  = 2 , exportSchema = false)
public abstract class AddFolderDatabase extends RoomDatabase {
    public abstract AddFolderDAO getAddFolder();

}
