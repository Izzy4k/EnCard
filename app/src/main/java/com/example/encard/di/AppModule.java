package com.example.encard.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.encard.App;
import com.example.encard.domain.Pixabay.PixabayApi;
import com.example.encard.domain.rapid.TranslateApi;
import com.example.encard.model.Image.PixaBayStorage;
import com.example.encard.model.translate.TranslateStorage;
import com.example.encard.model.video.VideoStorage;
import com.example.encard.ui.fragment.translate.TranslateViewModel;
import com.example.encard.ui.fragment.video.VideoViewModel;
import com.example.encard.ui.fragment.word.WordViewModel;
import com.example.encard.utils.EndPoints;
import com.example.encard.utils.Pref;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public PixabayApi provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PixabayApi.class);
    }

    @Provides
    @Singleton
    public TranslateApi translateApi() {
        return new Retrofit.Builder()
                .baseUrl("https://just-translated.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TranslateApi.class);
    }

    @Provides
    public PixaBayStorage pixaBayStorage(PixabayApi pixabayApi) {
        return new PixaBayStorage(pixabayApi);
    }

    @Provides
    public VideoStorage videoStorage(PixabayApi pixabayApi) {
        return new VideoStorage(pixabayApi);
    }

    @Provides
    public WordViewModel wordViewModel(PixaBayStorage pixaBayStorage) {
        return new WordViewModel(pixaBayStorage);
    }

    @Provides
    public VideoViewModel videoViewModel(VideoStorage videoStorage) {
        return new VideoViewModel(videoStorage);
    }

    @Provides
    public TranslateStorage translateStorage(TranslateApi translateApi) {
        return new TranslateStorage(translateApi);
    }

    @Provides
    public TranslateViewModel translateViewModel(PixaBayStorage pixaBayStorage, TranslateStorage translateStorage) {
        return new TranslateViewModel(pixaBayStorage, translateStorage);
    }


    @Provides
    public SharedPreferences sharedPreferences(@ApplicationContext Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(EndPoints.ABOBA,
                Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    @Provides
    public Pref pref(SharedPreferences sharedPreferences) {
        return new Pref(sharedPreferences);
    }

}
