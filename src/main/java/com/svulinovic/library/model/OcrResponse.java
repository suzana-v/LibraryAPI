package com.svulinovic.library.model;

import lombok.Data;

@Data
public class OcrResponse {

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private boolean valid;

}
