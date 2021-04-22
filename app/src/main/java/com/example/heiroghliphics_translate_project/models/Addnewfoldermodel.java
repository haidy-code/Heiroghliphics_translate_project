package com.example.heiroghliphics_translate_project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "placesfolder")
public class Addnewfoldermodel {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "foldername")
    private String foldername;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "image")
    private String image;

    public Addnewfoldermodel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


