package com.example.encard.domain.model.category.repo;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.category.dao.CategoryDao;
import com.example.encard.domain.model.category.entity.Category;

import java.util.List;

import javax.inject.Inject;

public class CategoryStorage {
    public CategoryDao categoryDao;

    @Inject
    public CategoryStorage(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void createCategory(String word) {
        categoryDao.insert(new Category(word));
    }

    public LiveData<List<Category>> getCategory() {
        return categoryDao.getAllList();
    }
}
