package com.example.encard.domain.rapid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateRetrofit {
    private TranslateRetrofit() {
    }

    private static TranslateApi translateApi;

    public static TranslateApi getTranslateApi() {
        if (translateApi == null) {
            translateApi = createApi();
        }
        return translateApi ;
    }

    private static TranslateApi createApi() {
        return new Retrofit.Builder()
                .baseUrl("https://just-translated.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TranslateApi.class);
    }
}
