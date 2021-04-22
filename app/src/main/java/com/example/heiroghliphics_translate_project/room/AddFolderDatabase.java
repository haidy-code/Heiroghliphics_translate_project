package com.example.heiroghliphics_translate_project.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.heiroghliphics_translate_project.models.Addnewfoldermodel;


@Database(entities = {Addnewfoldermodel.class} , version = 2 , exportSchema = false)
public abstract class AddFolderDatabase extends RoomDatabase {
    public abstract AddFolderDAO getAddFolder();

}
