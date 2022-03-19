package com.example.encard.ui.fragment.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.domain.model.category.entity.Category;
import com.example.encard.domain.model.category.repo.CategoryStorage;
import com.example.encard.domain.model.word.entity.WordEntity;
import com.example.encard.domain.model.word.repo.WordStorage;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CategoryViewModel extends ViewModel {
    public CategoryStorage categoryStorage;
    public WordStorage wordStorage;

    @Inject
    public CategoryViewModel(CategoryStorage categoryStorage, WordStorage wordStorage) {
        this.wordStorage = wordStorage;
        this.categoryStorage = categoryStorage;
    }

    public void createCategory(String word) {
        categoryStorage.createCategory(word);
    }

    public void deleteCategory(Category category) {
        categoryStorage.deleteCategory(category);
        wordStorage.deleteWords(wordStorage.getWordsToTag(category.getCategory()));
    }

    public LiveData<List<Category>> getList() {
        return categoryStorage.getCategory();
    }


}
