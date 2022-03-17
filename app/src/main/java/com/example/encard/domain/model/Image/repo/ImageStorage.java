package com.example.encard.domain.model.Image.repo;


import androidx.annotation.NonNull;

import com.example.encard.data.network.model.image.remote.ImageApi;
import com.example.encard.domain.model.Image.entity.PixaBayResponse;
import com.example.encard.utils.EndPoints;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageStorage {
    public ImageApi imageApi;

    @Inject
    public ImageStorage(ImageApi imageApi) {
        this.imageApi = imageApi;
    }

    public void getImageGyId(String word, int page, Result result) {
        imageApi.getImagePage(EndPoints.KEY, word, page, EndPoints.TEN)
                .enqueue(new Callback<PixaBayResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PixaBayResponse> call,
                                           @NonNull Response<PixaBayResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            result.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PixaBayResponse> call, @NonNull Throwable t) {
                        result.onFailure(t);
                    }
                });
    }

    public interface Result {
        void onSuccess(PixaBayResponse pixaBayResponse);

        void onFailure(Throwable throwable);
    }
}
