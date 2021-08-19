package com.svulinovic.library.retrofit.microblink;

import com.svulinovic.library.retrofit.microblink.model.BlinkIdEndpointResponse;
import com.svulinovic.library.retrofit.microblink.model.MRTDRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MicroblinkAPI {

    @POST("/v1/recognizers/mrtd")
    Call<BlinkIdEndpointResponse> scanMRZ(@Header("Authorization") String token,
                                          @Body MRTDRequest request);

}
