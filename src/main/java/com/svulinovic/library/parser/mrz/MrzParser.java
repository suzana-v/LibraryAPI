package com.svulinovic.library.parser.mrz;

public class MrzParser {

    private static final String ROW_SEPARATOR = "\n";
    private static final String FILLER = "<";
    private static final String VARIABLE_DATA_SEPARATOR = "<<";

    private static final int[] POSITION_WEIGHT = new int[]{7, 3, 1};
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String mrz;

    public MrzParser(String mrz) {
        if (mrz == null) {
            throw new RuntimeException("mrz is null");
        }
        this.mrz = mrz.replace(ROW_SEPARATOR, "");
    }

    public String extract(FixedLengthData type) {
        int index = type.getIndex();
        int length = type.getLength();

        String data = mrz.substring(index, index + length);
        return data.replace(FILLER, "");
    }

    public String extract(VariableLengthData type) {
        int index = type.getIndex();
        int length = type.getLength();

        String data = mrz.substring(index, index + length);
        String[] dataParts = data.split(VARIABLE_DATA_SEPARATOR);
        return dataParts[type.getPosition()].replace(FILLER, " ");
    }

    public boolean isValid(CheckDigit type) {
        int index = type.getIndex();
        String checkDigit = mrz.substring(index, index + 1);

        int dataCheckIndex = type.getDataCheck().getIndex();
        int dataCheckLength = type.getDataCheck().getLength();
        String dataCheck = mrz.substring(dataCheckIndex, dataCheckIndex + dataCheckLength);
        int calculatedCheckDigit = calculateCheckDigit(dataCheck);

        return checkDigit.equals(String.valueOf(calculatedCheckDigit));
    }

    public boolean isValid(CompositeCheckDigit type) {
        int index = type.getIndex();
        String checkDigit = mrz.substring(index, index + 1);

        StringBuilder dataCheck = new StringBuilder();
        for (FixedLengthData data : type.getDataCheck()) {
            int dataCheckIndex = data.getIndex();
            int dataCheckLength = data.getLength();
            dataCheck.append(mrz, dataCheckIndex, dataCheckIndex + dataCheckLength);
        }
        int calculatedCheckDigit = calculateCheckDigit(dataCheck.toString());

        return checkDigit.equals(String.valueOf(calculatedCheckDigit));
    }

    private int calculateCheckDigit(String in) {
        if (in == null || "".equals(in.trim())) {
            throw new RuntimeException("Failed to calculate check digit for: " + in);
        }
        int sum = 0;
        for (int i = 0; i < in.length(); i++) {
            int charWeight = getCharWeight(in.charAt(i));
            int posWeight = getPosWeight(i);
            sum += charWeight * posWeight;
        }
        return sum % 10;
    }

    private int getCharWeight(char in) {
        if (in == '<') {
            return 0;
        }
        for (int i = 0; i < ALPHABET.length(); i++) {
            if (in == ALPHABET.charAt(i)) {
                return i;
            }
        }
        throw new RuntimeException("Failed to calculate char weight for: " + in);
    }

    private int getPosWeight(int pos) {
        if (pos < 0) {
            throw new RuntimeException("Failed to calculate position weight for: " + pos);
        }
        int index = pos % 3;
        return POSITION_WEIGHT[index];
    }

}
