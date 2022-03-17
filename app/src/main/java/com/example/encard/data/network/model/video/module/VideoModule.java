package com.example.encard.data.network.model.video.module;


import androidx.annotation.NonNull;

import com.example.encard.data.network.model.image.module.ImageModule;
import com.example.encard.data.network.model.video.remote.VideoApi;
import com.example.encard.domain.model.video.repo.VideoStorage;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module(includes = {ImageModule.class})
@InstallIn(SingletonComponent.class)
public class VideoModule {

    @Provides
    @Singleton
    public VideoApi videoApi(@NonNull Retrofit retrofit) {
        return retrofit
                .create(VideoApi.class);
    }

    @Provides
    public VideoStorage videoStorage(VideoApi videoApi) {
        return new VideoStorage(videoApi);
    }


}
