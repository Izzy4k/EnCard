package com.example.encard.data.local.model.word.temp;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.word.dao.WordDao;
import com.example.encard.data.local.model.word.source.WordSource;
import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.List;

import javax.inject.Inject;

public class WordTemp implements WordSource {
    private final WordDao wordDao;

    @Inject
    public WordTemp(WordDao wordDao) {
        this.wordDao = wordDao;
    }


    @Override
    public void addWord(WordEntity wordEntity) {
        wordDao.createWord(wordEntity);
    }

    @Override
    public void deleteWords(List<WordEntity> wordEntities) {
        wordDao.deleteWords(wordEntities);
    }

    @Override
    public void deleteWord(WordEntity wordEntity) {
        wordDao.deleteWord(wordEntity);
    }

    @Override
    public LiveData<List<WordEntity>> getLiveList(String category) {
        return wordDao.getLiveListToTag(category);
    }

    @Override
    public List<WordEntity> getList(String category) {
        return wordDao.getListToTag(category);
    }
}
