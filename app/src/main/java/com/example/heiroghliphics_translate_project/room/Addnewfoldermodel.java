package com.example.heiroghliphics_translate_project.room;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "placesfolder")
public class Addnewfoldermodel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int folderid;
    @ColumnInfo(name = "foldername")
    private String foldername;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "image")
    private String image;
    private  int icons;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public int getIcons() {
        return icons;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public Addnewfoldermodel() {
    }

    public Addnewfoldermodel(String foldername, String date) {
        this.foldername = foldername;
        this.date = date;
    }

    public Addnewfoldermodel(String foldername, String date, String image) {
        this.foldername = foldername;
        this.date = date;
        this.image = image;
    }

    public Addnewfoldermodel(String foldername, int icons) {
        this.foldername = foldername;
        this.icons = icons;
    }

    public int getFolderid() {
        return folderid;
    }

    public void setFolderid(int folderid) {
        this.folderid = folderid;
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


}



