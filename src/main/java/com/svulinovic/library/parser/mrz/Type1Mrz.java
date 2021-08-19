package com.svulinovic.library.parser.mrz;

public interface Type1Mrz {

    String getDocumentCode();

    String getIssuer();

    String getDocumentNumber();

    String getOpt1();

    String getDateOfBirth();

    String getGender();

    String getDateOfExpiry();

    String getNationality();

    String getOpt2();

    String getPrimaryId();

    String getSecondaryId();

    boolean isValid();

}