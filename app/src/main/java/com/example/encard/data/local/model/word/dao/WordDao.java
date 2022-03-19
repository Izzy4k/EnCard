package com.example.encard.data.local.model.word.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.List;


@Dao
public interface WordDao {
    @Query("SELECT * FROM WordEntity WHERE category=:categoryName")
    LiveData<List<WordEntity>> getAllList(String categoryName);

    @Insert
    void createWord(WordEntity wordEntity);
}
