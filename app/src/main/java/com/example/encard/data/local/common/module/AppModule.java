package com.example.encard.data.local.common.module;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.encard.data.local.room.AppDataBase;
import com.example.encard.utils.EndPoints;
import com.example.encard.data.local.utils.Pref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public SharedPreferences sharedPreferences(@NonNull @ApplicationContext Context context) {
        return context.getSharedPreferences(EndPoints.ABOBA,
                Context.MODE_PRIVATE);
    }

    @Provides
    public Pref pref(SharedPreferences sharedPreferences) {
        return new Pref(sharedPreferences);
    }

    @Provides
    @Singleton
    public AppDataBase appDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, EndPoints.NAME_DATA_BASE).
                allowMainThreadQueries()
                .build();
    }


}
