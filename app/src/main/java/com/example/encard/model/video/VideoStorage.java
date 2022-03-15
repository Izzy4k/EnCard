package com.example.encard.model.video;

import androidx.annotation.NonNull;

import com.example.encard.domain.Pixabay.PixabayApi;
import com.example.encard.utils.EndPoints;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoStorage {
    PixabayApi pixabayApi;

    @Inject
    public VideoStorage(PixabayApi pixabayApi) {
        this.pixabayApi = pixabayApi;
    }

        public  void getVideoGyId(String word, Result result) {
        pixabayApi.getVideo(EndPoints.KEY, word).enqueue(new Callback<PixaBoyVideo>() {
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
