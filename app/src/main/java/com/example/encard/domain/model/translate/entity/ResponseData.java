package com.example.encard.domain.model.translate.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("translatedText")
    @Expose
    private String translatedText;
    @SerializedName("match")
    @Expose
    private Double match;

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public Double getMatch() {
        return match;
    }

    public void setMatch(Double match) {
        this.match = match;
    }
}
