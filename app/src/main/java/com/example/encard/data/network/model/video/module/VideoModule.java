package com.example.encard.data.network.model.video.module;


import com.example.encard.data.network.model.image.module.ImageModule;
import com.example.encard.data.network.model.video.remote.VideoApi;
import com.example.encard.domain.model.video.repo.VideoStorage;
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
public class VideoModule {

    @Provides
    @Singleton
    public VideoApi videoApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL_PIXA)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(VideoApi.class);
    }

    @Provides
    public VideoStorage videoStorage(VideoApi videoApi) {
        return new VideoStorage(videoApi);
    }


}
