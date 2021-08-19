package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class DefaultResponse {

    private Code code;
    private String summary;

    public enum Code {
        OK,
        UNCERTAIN,
        EMPTY,
        VERSION,
        DEFAULT,
        API_INFO,
        INTERNAL_SERVER_ERROR,
        SERVER_TOO_BUSY,
        SERVER_CANCELED_REQUEST,
        BAD_REQUEST,
        AUTHORIZATION_HEADER_IS_NOT_VALID,
        INSUFFICIENT_BALANCE,
        NOT_ALLOWED_TO_EXECUTE_REQUESTED_RECOGNIZER,
        API_ROLE_IS_NOT_FOUND,
        INVALID_LICENSE_KEY,
        RECOGNIZERS_IS_EMPTY,
        RECOGNIZER_IS_NOT_VALID,
        RECOGNIZER_IS_NOT_STRING,
        RECOGNIZERS_IS_NOT_ARRAY_OF_STRINGS,
        FORBIDDEN_RECOGNIZER,
        RECOGNIZER_OR_RECOGNIZERS_IS_REQUIRED,
        TOO_MANY_RECOGNIZERS_SENT,
        IMAGE_SIZE_IS_TOO_BIG,
        IMAGE_IS_NOT_VALID_BASE64_STRING,
        IMAGE_IS_NOT_ABLE_TO_CONVERT_TO_RAW_PIXELS,
        IMAGE_IS_NOT_VALID
    }

}
