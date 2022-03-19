package com.example.encard.data.local.model.word.module;

import androidx.annotation.NonNull;

import com.example.encard.data.local.common.module.AppModule;
import com.example.encard.data.local.model.word.dao.WordDao;
import com.example.encard.data.local.model.word.temp.WordTemp;
import com.example.encard.data.local.room.AppDataBase;
import com.example.encard.domain.model.word.repo.WordStorage;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module(includes = {AppModule.class})
@InstallIn({SingletonComponent.class})
public class WordModule {
    @Provides
    public WordDao wordDao(@NonNull AppDataBase appDataBase) {
        return appDataBase.wordDao();
    }

    @Provides
    public WordStorage wordStorage(WordTemp wordTemp) {
        return new WordStorage(wordTemp);
    }

    @Provides
    public WordTemp wordTemp(WordDao wordDao) {
        return new WordTemp(wordDao);
    }
}
