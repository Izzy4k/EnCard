package com.example.encard.data.local.model.category.temp;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.category.dao.CategoryDao;
import com.example.encard.data.local.model.category.source.CategorySource;
import com.example.encard.domain.model.category.entity.Category;

import java.util.List;

import javax.inject.Inject;

public class CategoryTemp implements CategorySource {
    private final CategoryDao categoryDao;

    @Inject
    public CategoryTemp(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void addCreate(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void deleteCreate(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public LiveData<List<Category>> getAllList() {
        return categoryDao.getAllList();
    }
}
