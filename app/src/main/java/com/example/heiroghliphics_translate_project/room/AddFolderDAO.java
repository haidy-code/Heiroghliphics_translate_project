package com.example.heiroghliphics_translate_project.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddFolderDAO {

    @Query("SELECT * FROM placesfolder")
    List<Addnewfoldermodel> getAllPlaces();

    @Insert
    void insertPlace(Addnewfoldermodel addnewfoldermodel);

    @Update
    void updatePlace(Addnewfoldermodel addnewfoldermodel);

    @Delete
    void deletePlace(Addnewfoldermodel addnewfoldermodel);



}
