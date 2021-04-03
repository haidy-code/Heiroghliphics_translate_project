package com.example.heiroghliphics_translate_project.models;

public class PlacesTranslationModel {
    private String date;
    private int image1;
    private int image2;
    private int image3;


    public PlacesTranslationModel() {
    }

    public PlacesTranslationModel(String date, int image1, int image2, int image3) {
        this.date = date;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
        this.image3 = image3;
    }


}
