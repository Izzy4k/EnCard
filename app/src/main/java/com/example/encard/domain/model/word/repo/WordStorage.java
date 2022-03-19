package com.example.encard.domain.model.word.repo;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.word.dao.WordDao;
import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.List;

import javax.inject.Inject;

public class WordStorage {

    public WordDao wordDao;

    @Inject
    public WordStorage(WordDao wordDao) {
        this.wordDao = wordDao;
    }

    public void create(WordEntity wordEntity) {
        wordDao.createWord(wordEntity);
    }

    public LiveData<List<WordEntity>> getWords(String category) {
        return wordDao.getAllList(category);
    }
}
