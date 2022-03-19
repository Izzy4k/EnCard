package com.example.encard.ui.fragment.word;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.domain.model.Image.repo.ImageStorage;
import com.example.encard.domain.model.Image.entity.PixaBayResponse;
import com.example.encard.domain.model.word.entity.WordEntity;
import com.example.encard.domain.model.word.repo.WordStorage;

import java.util.List;

import javax.inject.Inject;

public class WordViewModel extends ViewModel {
    private final MutableLiveData<String> errorMessage;
    private final ImageStorage imageStorage;
    private final WordStorage wordStorage;
    private Error error;

    @Inject
    public WordViewModel(ImageStorage imageStorage,
                         WordStorage wordStorage) {
        this.imageStorage = imageStorage;
        this.wordStorage = wordStorage;
        errorMessage = new MutableLiveData<>();
    }

    public void init(String word, int page, String category) {
        imageStorage.getImageGyId(word, page, new ImageStorage.Result() {
            @Override
            public void onSuccess(PixaBayResponse pixaBayResponse) {
                if (!pixaBayResponse.getHits().isEmpty())
                    wordStorage.create(word, category, pixaBayResponse
                            .getHits()
                            .get(0).getLargeImageURL());
                else error.nullPointer();
            }

            @Override
            public void onFailure(Throwable throwable) {
                errorMessage.setValue(throwable.getMessage());
            }
        });
    }

    public void delete(WordEntity wordEntity) {
        wordStorage.deleteWord(wordEntity);
    }

    public LiveData<List<WordEntity>> getWords(String category) {
        return wordStorage.getWords(category);
    }

    public void setError(Error error) {
        this.error = error;
    }


    public interface Error {
        void nullPointer();
    }
}
