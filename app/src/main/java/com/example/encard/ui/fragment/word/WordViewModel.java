package com.example.encard.ui.fragment.word;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.model.Image.PixaBayResponse;
import com.example.encard.model.Image.PixaBayStorage;

import javax.inject.Inject;

public class WordViewModel extends ViewModel {
    private final MutableLiveData<PixaBayResponse> responseMutableLiveData;
    private final PixaBayStorage pixaBayStorage;

    @Inject
    public WordViewModel(PixaBayStorage pixaBayStorage) {
        this.pixaBayStorage = pixaBayStorage;
        responseMutableLiveData = new MutableLiveData<>();
    }

    public void init(String word) {
        pixaBayStorage.getImageGyId(word, new PixaBayStorage.Result() {
            @Override
            public void onSuccess(PixaBayResponse pixaBayResponse) {
                responseMutableLiveData.setValue(pixaBayResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                responseMutableLiveData.setValue(null);
            }
        });
    }

    public MutableLiveData<PixaBayResponse> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }
}
