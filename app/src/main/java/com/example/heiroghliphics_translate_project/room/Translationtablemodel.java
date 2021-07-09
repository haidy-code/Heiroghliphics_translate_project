package com.example.heiroghliphics_translate_project.room;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "translationtable", foreignKeys = @ForeignKey(entity = Addnewfoldermodel.class,parentColumns = "folderid",childColumns = "whichfolder"))
public class Translationtablemodel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int trans_id;
    @ColumnInfo(name = "translation")
    private String translation;
    @ColumnInfo(name = "capturedimage")
    private String capturedimage;
    @ColumnInfo(name = "whichfolder")
    private int whichfolder;

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getCapturedimage() {
        return capturedimage;
    }

    public void setCapturedimage(String capturedimage) {
        this.capturedimage = capturedimage;
    }

    public int getWhichfolder() {
        return whichfolder;
    }

    public void setWhichfolder(int whichfolder) {
        this.whichfolder = whichfolder;
    }
}
