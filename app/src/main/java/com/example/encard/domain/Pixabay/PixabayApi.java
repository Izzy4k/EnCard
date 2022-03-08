package com.example.encard.domain.Pixabay;

import com.example.encard.model.PixaBayResponse;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET(EndPoints.API)
    Call<PixaBayResponse> getImage( @Query(EndPoints.APP_KEY) String key,@Query("q") String word);
}
