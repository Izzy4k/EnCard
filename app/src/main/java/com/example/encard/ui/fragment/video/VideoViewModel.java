package com.example.encard.ui.fragment.video;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.model.video.PixaBoyVideo;
import com.example.encard.model.video.VideoStorage;

public class VideoViewModel extends ViewModel {
    private final MutableLiveData<PixaBoyVideo> pixaBoyVideoMutableLiveData;

    public VideoViewModel() {
        pixaBoyVideoMutableLiveData = new MutableLiveData<>();
    }
    public void init(String word){
        VideoStorage.getVideoGyId(word, new VideoStorage.Result() {
            @Override
            public void onSuccess(PixaBoyVideo pixaBoyVideo) {
                pixaBoyVideoMutableLiveData.setValue(pixaBoyVideo);
            }

            @Override
            public void onFailure(Throwable throwable) {
                pixaBoyVideoMutableLiveData.setValue(null);
            }
        });
    }

    public MutableLiveData<PixaBoyVideo> getPixaBoyVideoMutableLiveData() {
        return pixaBoyVideoMutableLiveData;
    }
}
