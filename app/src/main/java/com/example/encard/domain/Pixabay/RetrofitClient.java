package com.example.encard.domain.Pixabay;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private RetrofitClient() {
    }

    private static PixabayApi pixabayApi;

    public static PixabayApi getPixabay() {
        if (pixabayApi == null) {
            pixabayApi = createPixaApi();
        }
        return pixabayApi;
    }

    private static PixabayApi createPixaApi() {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PixabayApi.class);
    }
}
