package com.example.heiroghliphics_translate_project.room;

import android.content.Context;

import androidx.room.Room;

public class RoomFactory {
    private static AddFolderDatabase database;

    public static AddFolderDatabase getDatabase(Context context) {
        if(database==null){
            database= Room.databaseBuilder(context,AddFolderDatabase.class,)
        }
        return null;
    }

}
