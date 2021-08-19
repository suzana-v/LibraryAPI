package com.svulinovic.library.parser.mrz;

import lombok.Data;

@Data
public class CompositeCheckDigit extends FixedLengthData {

    private FixedLengthData[] dataCheck;

    public CompositeCheckDigit(int index, FixedLengthData[] dataCheck) {
        super(index, 1);
        this.dataCheck = dataCheck;
    }

}
