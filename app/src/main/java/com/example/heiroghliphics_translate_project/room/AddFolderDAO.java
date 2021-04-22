package com.example.heiroghliphics_translate_project.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.heiroghliphics_translate_project.models.Addnewfoldermodel;

import java.util.List;

@Dao
public interface AddFolderDAO {
//    @Query("SELECT * FROM Addnewfoldermodel")
//    List<Addnewfoldermodel> getAllPlaces();
    @Insert
    void insert place(Addnewfoldermodel )

}
