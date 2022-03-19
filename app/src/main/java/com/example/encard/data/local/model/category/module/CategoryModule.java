package com.example.encard.data.local.model.category.module;

import androidx.annotation.NonNull;

import com.example.encard.data.local.common.module.AppModule;
import com.example.encard.data.local.room.AppDataBase;
import com.example.encard.data.local.model.category.dao.CategoryDao;
import com.example.encard.domain.model.category.repo.CategoryStorage;


import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module(includes = {AppModule.class})
@InstallIn(SingletonComponent.class)
public class CategoryModule {
    @Provides
    public CategoryDao categoryDao(@NonNull AppDataBase appDataBase) {
        return appDataBase.categoryDao();
    }

    @Provides
    public CategoryStorage categoryStorage(CategoryDao categoryDao) {
        return new CategoryStorage(categoryDao);
    }
}
