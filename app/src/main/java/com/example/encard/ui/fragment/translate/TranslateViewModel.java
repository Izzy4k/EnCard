package com.example.encard.ui.fragment.translate;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.model.Image.PixaBayResponse;
import com.example.encard.model.Image.PixaBayStorage;
import com.example.encard.model.translate.Translate;
import com.example.encard.model.translate.TranslateStorage;

public class TranslateViewModel extends ViewModel {
    private final MutableLiveData<Translate> translateMutableLiveData;
    private final MutableLiveData<PixaBayResponse> pixaBayResponseMutableLiveData;

    public TranslateViewModel() {
        translateMutableLiveData = new MutableLiveData<>();
        pixaBayResponseMutableLiveData = new MutableLiveData<>();
    }

    public void initTranslate(String word) {
        TranslateStorage.getTranslateGyId(word, new TranslateStorage.Result() {
            @Override
            public void onSuccess(Translate translate) {
                translateMutableLiveData.setValue(translate);
            }

            @Override
            public void onFailure(Throwable throwable) {
                translateMutableLiveData.setValue(null);
            }
        });
    }

    public void initImage(String word) {
        PixaBayStorage.getImageGyId(word, new PixaBayStorage.Result() {
            @Override
            public void onSuccess(PixaBayResponse pixaBayResponse) {
                pixaBayResponseMutableLiveData.setValue(pixaBayResponse);
            }

            @Override
            public void onFailure(Throwable throwable) {
                pixaBayResponseMutableLiveData.setValue(null);
            }
        });
    }

    public MutableLiveData<Translate> getTranslateMutableLiveData() {
        return translateMutableLiveData;
    }

    public MutableLiveData<PixaBayResponse> getPixaBayResponseMutableLiveData() {
        return pixaBayResponseMutableLiveData;
    }
}
