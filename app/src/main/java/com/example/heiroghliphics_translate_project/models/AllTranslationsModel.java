package com.example.heiroghliphics_translate_project.models;

import java.io.Serializable;

public class AllTranslationsModel implements Serializable {
    private int TranslatedImage;

    public AllTranslationsModel()  {

    }
    public AllTranslationsModel(int translatedImage) {
        TranslatedImage = translatedImage;
    }

    public int getTranslatedImage() {
        return TranslatedImage;
    }

    public void setTranslatedImage(int translatedImage) {
        TranslatedImage = translatedImage;
    }

}
