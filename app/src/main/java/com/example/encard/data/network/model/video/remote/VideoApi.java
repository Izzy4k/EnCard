package com.example.encard.data.network.model.video.remote;

import com.example.encard.domain.model.video.entity.PixaBoyVideo;
import com.example.encard.utils.EndPoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoApi {
    @GET(EndPoints.VIDEOS_API)
    Call<PixaBoyVideo> getVideo(@Query(EndPoints.APP_KEY) String key,
                                @Query(EndPoints.Q) String word);
}
