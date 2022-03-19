package com.example.encard.data.network.model.translate.remote;

import com.example.encard.domain.model.translate.entity.Translate;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TranslateApi {
    @GET(EndPoints.GET_API)
    Call<Translate> getTranslate(
            @Header("X-RapidAPI-Host") String host,@Header("X-RapidAPI-Key") String key
            ,@Query(EndPoints.LANG) String language,
                                 @Query(EndPoints.TEXT) String word );
}
