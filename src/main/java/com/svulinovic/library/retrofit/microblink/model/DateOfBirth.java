package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class DateOfBirth {

    private int day;
    private int month;
    private int year;
    private boolean successfullyParsed;
    private String originalString;

}
