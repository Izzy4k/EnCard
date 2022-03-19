package com.example.encard.data.local.model.category.source;


import androidx.lifecycle.LiveData;

import com.example.encard.domain.model.category.entity.Category;

import java.util.List;


public interface CategorySource {
    void addCreate(Category category);

    void deleteCreate(Category category);

    LiveData<List<Category>> getAllList();
}
