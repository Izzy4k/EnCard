package com.example.encard.data.local.model.word.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.List;


@Dao
public interface WordDao {
    @Query("SELECT * FROM WordEntity WHERE category=:categoryName")
    LiveData<List<WordEntity>> getLiveListToTag(String categoryName);

    @Insert
    void createWord(WordEntity wordEntity);

    @Delete
    void deleteWords(List<WordEntity> wordEntities);

    @Delete
    void deleteWord(WordEntity wordEntity);

    @Query("SELECT * FROM WordEntity WHERE category=:categoryName")
    List<WordEntity> getListToTag(String categoryName);
}
