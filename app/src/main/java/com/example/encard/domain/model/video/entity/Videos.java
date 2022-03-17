package com.example.encard.domain.model.video.entity;

import com.example.encard.domain.model.video.entity.Large;
import com.example.encard.domain.model.video.entity.Medium;
import com.example.encard.domain.model.video.entity.Small;
import com.example.encard.domain.model.video.entity.Tiny;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("tiny")
    @Expose
    private Tiny tiny;

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Small getSmall() {
        return small;
    }

    public void setSmall(Small small) {
        this.small = small;
    }

    public Tiny getTiny() {
        return tiny;
    }

    public void setTiny(Tiny tiny) {
        this.tiny = tiny;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "large=" + large.getUrl() +
                ", medium=" + medium.getUrl() +
                ", small=" + small.getUrl() +
                ", tiny=" + tiny.getUrl() +
                '}';
    }
}
