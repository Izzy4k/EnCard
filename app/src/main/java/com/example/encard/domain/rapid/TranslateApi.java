package com.example.encard.domain.rapid;

import com.example.encard.model.translate.Translate;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TranslateApi {
    @GET("/")
    Call<Translate> getTranslate(
            @Header("X-RapidAPI-Host") String host,@Header("X-RapidAPI-Key") String key
            ,@Query(EndPoints.LANG) String language,
                                 @Query(EndPoints.TEXT) String word );
}
