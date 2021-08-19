package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class BlinkIdEndpointResponse {

    private String executionId;
    private String finishTime;
    private String startTime;
    private Result result;

}
