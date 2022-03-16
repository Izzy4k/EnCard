package com.example.encard.ui.fragment.word;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.model.Image.PixaBayResponse;
import com.example.encard.model.Image.PixaBayStorage;

import javax.inject.Inject;

public class WordViewModel extends ViewModel  {
    private final MutableLiveData<PixaBayResponse> responseMutableLiveData;
    private final PixaBayStorage pixaBayStorage;
    private final MutableLiveData<String> errorMessage;
    private Error error;

    @Inject
    public WordViewModel(PixaBayStorage pixaBayStorage) {
        this.pixaBayStorage = pixaBayStorage;
        responseMutableLiveData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void init(String word, int page) {
        pixaBayStorage.getImageGyId(word, page, new PixaBayStorage.Result() {
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
