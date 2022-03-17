package com.example.encard.domain.model.video.repo;

import androidx.annotation.NonNull;

import com.example.encard.data.network.model.video.remote.VideoApi;
import com.example.encard.domain.model.video.entity.PixaBoyVideo;
import com.example.encard.utils.EndPoints;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoStorage {
    VideoApi videoApi;

    @Inject
    public VideoStorage(VideoApi videoApi) {
        this.videoApi = videoApi;
    }

    public void getVideoGyId(String word, Result result) {
        videoApi.getVideo(EndPoints.KEY, word).enqueue(new Callback<PixaBoyVideo>() {
            @Override
            public void onResponse(@NonNull Call<PixaBoyVideo> call, @NonNull Response<PixaBoyVideo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixaBoyVideo> call, @NonNull Throwable t) {
                result.onFailure(t);
            }
        });
    }

    public interface Result {
        void onSuccess(PixaBoyVideo pixaBoyVideo);

        void onFailure(Throwable throwable);
    }
}
