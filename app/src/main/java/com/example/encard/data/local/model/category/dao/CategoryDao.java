package com.example.encard.data.local.model.category.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.encard.domain.model.category.entity.Category;

import java.util.List;
@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAllList();
}
