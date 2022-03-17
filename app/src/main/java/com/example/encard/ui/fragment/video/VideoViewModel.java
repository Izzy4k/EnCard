package com.example.encard.ui.fragment.video;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.encard.domain.model.video.entity.PixaBoyVideo;
import com.example.encard.domain.model.video.repo.VideoStorage;

import javax.inject.Inject;

public class VideoViewModel extends ViewModel {
    private final MutableLiveData<PixaBoyVideo> pixaBoyVideoMutableLiveData;
    private final MutableLiveData<String> errorMessage;
    private final VideoStorage videoStorage;
    private Exception exception;

    @Inject
    public VideoViewModel(VideoStorage videoStorage) {
        this.videoStorage = videoStorage;
        pixaBoyVideoMutableLiveData = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void init(String word) {
        videoStorage.getVideoGyId(word, new VideoStorage.Result() {
            @Override
            public void onSuccess(PixaBoyVideo pixaBoyVideo) {
                if (!pixaBoyVideo.getHits().isEmpty())
                    pixaBoyVideoMutableLiveData.setValue(pixaBoyVideo);
                else exception.errorVideo();
            }

            @Override
            public void onFailure(Throwable throwable) {
                errorMessage.setValue(throwable.getMessage());
            }
        });
    }

    public MutableLiveData<PixaBoyVideo> getPixaBoyVideoMutableLiveData() {
        return pixaBoyVideoMutableLiveData;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public interface Exception {
        void errorVideo();
    }

}
