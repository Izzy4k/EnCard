package com.example.encard.data.local.module;

import com.example.encard.data.network.model.image.module.ImageModule;
import com.example.encard.data.network.model.translate.module.TranslateModule;
import com.example.encard.data.network.model.video.module.VideoModule;
import com.example.encard.domain.model.Image.repo.ImageStorage;
import com.example.encard.domain.model.translate.repo.TranslateStorage;
import com.example.encard.domain.model.video.repo.VideoStorage;
import com.example.encard.ui.fragment.translate.TranslateViewModel;
import com.example.encard.ui.fragment.video.VideoViewModel;
import com.example.encard.ui.fragment.word.WordViewModel;


import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
@Module(includes = {ImageModule.class,TranslateModule.class,VideoModule.class})
@InstallIn(SingletonComponent.class)
public class ViewModelModule {

    @Provides
    public WordViewModel wordViewModel(ImageStorage imageStorage) {
        return new WordViewModel(imageStorage);
    }

    @Provides
    public TranslateViewModel translateViewModel(ImageStorage imageStorage,
                                                 TranslateStorage translateStorage) {
        return new TranslateViewModel(imageStorage, translateStorage);
    }

    @Provides
    public VideoViewModel videoViewModel(VideoStorage videoStorage) {
        return new VideoViewModel(videoStorage);
    }
}
