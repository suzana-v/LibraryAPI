package com.svulinovic.library.parser.mrz;

import lombok.Data;

@Data
public class CheckDigit extends FixedLengthData {

    private FixedLengthData dataCheck;

    public CheckDigit(int index, FixedLengthData dataCheck) {
        super(index, 1);
        this.dataCheck = dataCheck;
    }
}
