package com.example.heiroghliphics_translate_project.room;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "symbols_table")
public class Symbolstablemodel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long symbol_id;
    @ColumnInfo(name = "symbolpath")
    private String symbolpath;
    @ColumnInfo(name = "whichtranslation")
    private int whichtranslation;

    public long getSymbol_id() {
        return symbol_id;
    }

    public void setSymbol_id(long symbol_id) {
        this.symbol_id = symbol_id;
    }

    public String getSymbolpath() {
        return symbolpath;
    }

    public void setSymbolpath(String symbolpath) {
        this.symbolpath = symbolpath;
    }

    public int getWhichtranslation() {
        return whichtranslation;
    }

    public void setWhichtranslation(int whichtranslation) {
        this.whichtranslation = whichtranslation;
    }
}
