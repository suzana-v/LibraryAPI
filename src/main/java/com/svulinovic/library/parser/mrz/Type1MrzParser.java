package com.svulinovic.library.parser.mrz;

public class Type1MrzParser extends MrzParser implements Type1Mrz {

    private static final FixedLengthData DOCUMENT_CODE = new FixedLengthData(0, 2);
    private static final FixedLengthData ISSUER = new FixedLengthData(2, 3);
    private static final FixedLengthData DOCUMENT_NUMBER = new FixedLengthData(5, 9);
    private static final CheckDigit DOCUMENT_NUMBER_CHECK_DIGIT = new CheckDigit(14, DOCUMENT_NUMBER);
    private static final FixedLengthData OPT_1 = new FixedLengthData(15, 15);
    private static final FixedLengthData DATE_OF_BIRTH = new FixedLengthData(30, 6);
    private static final CheckDigit DATE_OF_BIRTH_CHECK_DIGIT = new CheckDigit(36, DATE_OF_BIRTH);
    private static final FixedLengthData GENDER = new FixedLengthData(37, 1);
    private static final FixedLengthData DATE_OF_EXPIRY = new FixedLengthData(38, 6);
    private static final CheckDigit DATE_OF_EXPIRY_CHECK_DIGIT = new CheckDigit(44, DATE_OF_EXPIRY);
    private static final FixedLengthData NATIONALITY = new FixedLengthData(45, 3);
    private static final FixedLengthData OPT_2 = new FixedLengthData(48, 11);
    private static final CompositeCheckDigit ROW_1_2_CHECK_DIGIT = new CompositeCheckDigit(59,
            new FixedLengthData[] {DOCUMENT_NUMBER, DOCUMENT_NUMBER_CHECK_DIGIT, OPT_1, DATE_OF_BIRTH,
                    DATE_OF_BIRTH_CHECK_DIGIT, DATE_OF_EXPIRY, DATE_OF_EXPIRY_CHECK_DIGIT});
    private static final VariableLengthData PRIMARY_ID = new VariableLengthData(60, 30, 0);
    private static final VariableLengthData SECONDARY_ID = new VariableLengthData(60, 30, 1);

    private String dateOfBirth;
    private String primaryId;
    private String secondaryId;
    private Boolean valid;

    public Type1MrzParser(String mrz) {
        super(mrz);
    }

    @Override
    public String getDocumentCode() {
        return extract(DOCUMENT_CODE);
    }

    @Override
    public String getIssuer() {
        return extract(ISSUER);
    }

    @Override
    public String getDocumentNumber() {
        return extract(DOCUMENT_NUMBER);
    }

    @Override
    public String getOpt1() {
        return extract(OPT_1);
    }

    @Override
    public String getDateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = extract(DATE_OF_BIRTH);
        }
        return dateOfBirth;
    }

    @Override
    public String getGender() {
        return extract(GENDER);
    }

    @Override
    public String getDateOfExpiry() {
        return extract(DATE_OF_EXPIRY);
    }

    @Override
    public String getNationality() {
        return extract(NATIONALITY);
    }

    @Override
    public String getOpt2() {
        return extract(OPT_2);
    }

    @Override
    public String getPrimaryId() {
        if (primaryId == null) {
            primaryId = extract(PRIMARY_ID);
        }
        return primaryId;
    }

    @Override
    public String getSecondaryId() {
        if (secondaryId == null) {
            secondaryId = extract(SECONDARY_ID);
        }
        return secondaryId;
    }

    @Override
    public boolean isValid() {
        if (valid == null) {
            valid = isValid(DOCUMENT_NUMBER_CHECK_DIGIT) && isValid(DATE_OF_BIRTH_CHECK_DIGIT)
                    && isValid(DATE_OF_EXPIRY_CHECK_DIGIT) && isValid(ROW_1_2_CHECK_DIGIT);
        }
        return valid;
    }

}
