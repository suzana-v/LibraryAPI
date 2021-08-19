package com.svulinovic.library.retrofit.microblink.model;

import lombok.Data;

@Data
public class MRTDRequest {

    private Boolean returnFullDocumentImage; //Defines whether full document image should be extracted. Default FALSE
    private Boolean returnFaceImage; //Defines whether face image should be extracted. Default FALSE
    private Boolean returnSignatureImage; //Defines whether signature image should be extracted. Default FALSE
    private Boolean allowBlurFilter; //Defines whether blurred frames filtering is allowed. Default FALSE
    private Boolean allowUnparsedMrzResults; //Defines whether returning of unparsed MRZ (Machine Readable Zone) results is allowed. Default FALSE
    private Boolean allowUnverifiedMrzResults; //Defines whether returning unverified MRZ (Machine Readable Zone) results is allowed. Default TRUE
    private Boolean validateResultCharacters; //Whether result characters validation is performed. If a result member contains invalid character, the result state cannot be valid. Default TRUE
    private AnonymizationMode anonymizationMode; //Whether sensitive data should be removed from images, result fields or both. The setting only applies to certain documents. Default FULL_RESULT
    private Boolean anonymizeImage; //Deprecated. Defines whether sensitive data should be anonymized in full document image result.
    private Integer ageLimit; //Defines the age limit for age verification.
    private String imageSource; //Manadatory. Image source url or base64 encoded image.

    public enum AnonymizationMode {
        NONE,
        IMAGE_ONLY,
        RESULT_FIELDS_ONLY,
        FULL_RESULT
    }

}
