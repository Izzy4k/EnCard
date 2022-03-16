package com.example.encard.ui.fragment.translate;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.model.Image.PixaBayResponse;
import com.example.encard.model.Image.PixaBayStorage;
import com.example.encard.model.translate.Translate;
import com.example.encard.model.translate.TranslateStorage;
import com.example.encard.ui.fragment.word.WordViewModel;

import javax.inject.Inject;

public class TranslateViewModel extends ViewModel {
    private final MutableLiveData<Translate> translateMutableLiveData;
    private final MutableLiveData<PixaBayResponse> pixaBayResponseMutableLiveData;
    private final MutableLiveData<String> errorMessageTranslate;
    private final MutableLiveData<String> errorMessageImage;
    private final PixaBayStorage pixaBayStorage;
    private final TranslateStorage translateStorage;
    private Exception exception;

    @Inject
    public TranslateViewModel(PixaBayStorage pixaBayStorage, TranslateStorage translateStorage) {
        this.pixaBayStorage = pixaBayStorage;
        this.translateStorage = translateStorage;
        translateMutableLiveData = new MutableLiveData<>();
        pixaBayResponseMutableLiveData = new MutableLiveData<>();
        errorMessageTranslate = new MutableLiveData<>();
        errorMessageImage = new MutableLiveData<>();
    }

    public void initTranslate(String word) {
        translateStorage.getTranslateGyId(word, new TranslateStorage.Result() {
            @Override
            public void onSuccess(Translate translate) {
                translateMutableLiveData.setValue(translate);
            }

            @Override
            public void onFailure(Throwable throwable) {
                errorMessageTranslate.setValue(throwable.getMessage());
            }
        });
    }

    public void initImage(String word) {
        int page = 1;
        pixaBayStorage.getImageGyId(word, page, new PixaBayStorage.Result() {
            @Override
            public void onSuccess(PixaBayResponse pixaBayResponse) {
                if (!pixaBayResponse.getHits().isEmpty())
                    pixaBayResponseMutableLiveData.setValue(pixaBayResponse);
                else exception.errorImage();
            }

            @Override
            public void onFailure(Throwable throwable) {
                errorMessageImage.setValue(throwable.getMessage());
            }
        });
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public MutableLiveData<Translate> getTranslateMutableLiveData() {
        return translateMutableLiveData;
    }

    public MutableLiveData<PixaBayResponse> getPixaBayResponseMutableLiveData() {
        return pixaBayResponseMutableLiveData;
    }

    public MutableLiveData<String> getErrorMessageTranslate() {
        return errorMessageTranslate;
    }

    public MutableLiveData<String> getErrorMessageImage() {
        return errorMessageImage;
    }

    public interface Exception {
        void errorImage();

    }
}
