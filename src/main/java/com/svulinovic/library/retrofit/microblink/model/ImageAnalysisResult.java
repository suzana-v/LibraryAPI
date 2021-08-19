package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class ImageAnalysisResult {

    private boolean blurred;
    private String documentImageColorStatus;
    private String documentImageMoireStatus;
    private String faceDetectionStatus;
    private String mrzDetectionStatus;
    private String barcodeDetectionStatus;

}
