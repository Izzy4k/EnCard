package com.example.encard.data.network.model.image.remote;

import com.example.encard.domain.model.Image.entity.PixaBayResponse;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
    @GET(EndPoints.API)
    Call<PixaBayResponse> getImagePage(@Query(EndPoints.APP_KEY) String key,
                                       @Query(EndPoints.Q) String word
            , @Query(EndPoints.PAGE) int page , @Query(EndPoints.LIMIT) int limit);
}
