package com.example.encard.ui.fragment.word;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.domain.model.Image.repo.ImageStorage;
import com.example.encard.domain.model.Image.entity.PixaBayResponse;

import javax.inject.Inject;

public class WordViewModel extends ViewModel  {
    private final MutableLiveData<PixaBayResponse> responseMutableLiveData;
    private final ImageStorage imageStorage;
    private final MutableLiveData<String> errorMessage;
    private Error error;

    @Inject
    public WordViewModel(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
        responseMutableLiveData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void init(String word, int page) {
        imageStorage.getImageGyId(word, page, new ImageStorage.Result() {
            @Override
            public void onSuccess(PixaBayResponse pixaBayResponse) {
                if (!pixaBayResponse.getHits().isEmpty())
                    responseMutableLiveData.setValue(pixaBayResponse);
                else error.nullPointer();
            }

            @Override
            public void onFailure(Throwable throwable) {
                errorMessage.setValue(throwable.getMessage());
            }
        });
    }

    public void setError(Error error) {
        this.error = error;
    }

    public MutableLiveData<PixaBayResponse> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public interface Error {
        void nullPointer();
    }
}
