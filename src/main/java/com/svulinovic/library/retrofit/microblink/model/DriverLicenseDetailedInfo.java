package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class DriverLicenseDetailedInfo {

    private String restrictions;
    private String endorsements;
    private String vehicleClass;
    private String conditions;

}
