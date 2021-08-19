package com.svulinovic.library.retrofit.microblink;

import com.google.gson.Gson;
import com.svulinovic.library.config.MicroblinkProperties;
import com.svulinovic.library.exception.MicroblinkException;
import com.svulinovic.library.retrofit.RetrofitHelper;
import com.svulinovic.library.retrofit.microblink.model.BlinkIdEndpointResponse;
import com.svulinovic.library.retrofit.microblink.model.DefaultResponse;
import com.svulinovic.library.retrofit.microblink.model.MRTDRequest;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

@Component
public class MicroblinkClient {

    private final MicroblinkProperties properties;
    private final Gson gson = new Gson();
    private MicroblinkAPI api;

    public MicroblinkClient(MicroblinkProperties properties) {
        this.properties = properties;
    }

    private MicroblinkAPI getAPI() {
        if (api == null) {
            api = RetrofitHelper.getMicroblinkAPI(properties.getEndpoint());
        }
        return api;
    }

    private String getToken() {
        return properties.getToken();
    }

    public <T> MicroblinkException microblinkException(Response<T> response) {
        if (response != null && response.errorBody() != null) {
            String errorBody;
            try {
                errorBody = response.errorBody().string();
            } catch (IOException e) {
                return new MicroblinkException("Failed to parse error response", e);
            }
            DefaultResponse e =  gson.fromJson(errorBody, DefaultResponse.class);
            return new MicroblinkException(e.getSummary());
        }
        return new MicroblinkException("Invalid service response");
    }

    public BlinkIdEndpointResponse scanMRZ(String image) {
        Response<BlinkIdEndpointResponse> response;
        try {
            MRTDRequest request = new MRTDRequest();
            request.setImageSource(image);

            response = getAPI().scanMRZ(getToken(), request).execute();

        } catch (IOException e) {
            throw new MicroblinkException("Service Unavailable", e);
        }

        if (response != null && response.isSuccessful() && response.body() != null) {
            return response.body();
        }

        throw microblinkException(response);
    }

}
