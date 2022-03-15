package com.example.encard.domain.Pixabay;

import com.example.encard.model.Image.PixaBayResponse;
import com.example.encard.model.video.PixaBoyVideo;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET(EndPoints.API)
    Call<PixaBayResponse> getImage(@Query(EndPoints.APP_KEY) String key,
                                   @Query(EndPoints.Q) String word
            , @Query(EndPoints.PAGE) int page);

    @GET(EndPoints.VIDEOS_API)
    Call<PixaBoyVideo> getVideo(@Query(EndPoints.APP_KEY) String key, @Query(EndPoints.Q) String word);
}
