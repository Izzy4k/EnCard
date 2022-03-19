package com.example.encard.domain.model.word.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordEntity {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String word ;
    private String category ;
    private String image ;

    public WordEntity(String word, String category, String image) {
        this.word = word;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
