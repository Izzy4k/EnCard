package com.example.encard.domain.model.word.repo;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.word.source.WordSource;
import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.List;

import javax.inject.Inject;

public class WordStorage {
    private final WordSource wordSource;

    @Inject
    public WordStorage(WordSource wordSource) {
        this.wordSource = wordSource;
    }

    public void create(String word, String category, String image) {
        wordSource.addWord(new WordEntity(word, category, image));
    }

    public void deleteWords(List<WordEntity> wordEntityList) {
        wordSource.deleteWords(wordEntityList);
    }
    public void deleteWord(WordEntity wordEntity){
        wordSource.deleteWord(wordEntity);
    }

    public LiveData<List<WordEntity>> getWords(String category) {
        return wordSource.getLiveList(category);
    }

    public List<WordEntity> getWordsToTag(String category) {
        return wordSource.getList(category);
    }
}
