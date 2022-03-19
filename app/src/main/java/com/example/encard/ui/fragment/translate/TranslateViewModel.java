package com.example.encard.ui.fragment.translate;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.domain.model.Image.entity.PixaBayResponse;
import com.example.encard.domain.model.Image.repo.ImageStorage;
import com.example.encard.domain.model.translate.entity.Translate;
import com.example.encard.domain.model.translate.repo.TranslateStorage;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TranslateViewModel extends ViewModel {
    private final MutableLiveData<Translate> translateMutableLiveData;
    private final TranslateStorage translateStorage;

    @Inject
    public TranslateViewModel(TranslateStorage translateStorage) {
        this.translateStorage = translateStorage;
        translateMutableLiveData = new MutableLiveData<>();
    }

    public void initTranslate(String word) {
        translateStorage.getTranslateGyId(word, new TranslateStorage.Result() {
            @Override
            public void onSuccess(Translate translate) {
                translateMutableLiveData.setValue(translate);
            }

            @Override
            public void onFailure(Throwable throwable) {
            }
        });
    }



    public MutableLiveData<Translate> getTranslateMutableLiveData() {
        return translateMutableLiveData;
    }
}






