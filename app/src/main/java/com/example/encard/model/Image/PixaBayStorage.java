package com.example.encard.model.Image;

import androidx.annotation.NonNull;

import com.example.encard.domain.Pixabay.RetrofitClient;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaBayStorage {
    public static void getImageGyId(String word, Result result) {
        RetrofitClient.getPixabay().getImage(EndPoints.KEY, word).enqueue(new Callback<PixaBayResponse>() {
            @Override
            public void onResponse(@NonNull Call<PixaBayResponse> call, @NonNull Response<PixaBayResponse> response) {
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
