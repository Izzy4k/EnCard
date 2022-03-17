package com.example.encard.data.network.model.translate.module;

import androidx.annotation.NonNull;

import com.example.encard.data.network.model.image.module.ImageModule;
import com.example.encard.data.network.model.translate.remote.TranslateApi;
import com.example.encard.domain.model.translate.repo.TranslateStorage;
import com.example.encard.utils.EndPoints;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ImageModule.class})
@InstallIn(SingletonComponent.class)
public class TranslateModule {
    @Provides
    @Singleton
    public TranslateApi translateApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL_TRANSLATE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TranslateApi.class);
    }

    @Provides
    public TranslateStorage translateStorage(TranslateApi translateApi) {
        return new TranslateStorage(translateApi);
    }

}
