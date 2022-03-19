package com.example.encard.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.encard.data.local.model.category.dao.CategoryDao;
import com.example.encard.data.local.model.word.dao.WordDao;
import com.example.encard.domain.model.category.entity.Category;
import com.example.encard.domain.model.word.entity.WordEntity;

@Database(entities = {Category.class, WordEntity.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract WordDao wordDao();
}
