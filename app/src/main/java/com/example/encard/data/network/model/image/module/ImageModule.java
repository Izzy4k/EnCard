package com.example.encard.data.network.model.image.module;


import androidx.annotation.NonNull;

import com.example.encard.data.network.model.image.remote.ImageApi;
import com.example.encard.domain.model.Image.repo.ImageStorage;
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

@Module
@InstallIn(SingletonComponent.class)
public class ImageModule {
    @Provides
    @Singleton
    public ImageApi imageApi(@NonNull Retrofit retrofit) {
        return retrofit.create(ImageApi.class);
    }

    @Provides
    public ImageStorage imageStorage(ImageApi imageApi) {
        return new ImageStorage(imageApi);
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL_PIXA)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
