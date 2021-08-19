package com.svulinovic.library.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OcrRequest {

    @NotBlank
    private String image;

}
