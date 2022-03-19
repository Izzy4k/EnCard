package com.example.encard.domain.model.category.repo;

import androidx.lifecycle.LiveData;

import com.example.encard.data.local.model.category.dao.CategoryDao;
import com.example.encard.data.local.model.category.source.CategorySource;
import com.example.encard.domain.model.category.entity.Category;

import java.util.List;

import javax.inject.Inject;

public class CategoryStorage {
    public final CategorySource categorySource;

    @Inject
    public CategoryStorage(CategorySource categorySource) {
        this.categorySource = categorySource;
    }

    public void createCategory(String word) {
        categorySource.addCreate(new Category(word));
    }
    public void deleteCategory(Category category){
        categorySource.deleteCreate(category);
    }

    public LiveData<List<Category>> getCategory() {
        return categorySource.getAllList();
    }
}
