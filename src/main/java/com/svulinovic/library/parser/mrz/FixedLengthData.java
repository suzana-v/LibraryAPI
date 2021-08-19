package com.svulinovic.library.parser.mrz;

import lombok.Data;

@Data
public class FixedLengthData {

    private int index;
    private int length;

    public FixedLengthData(int index, int length) {
        this.index = index;
        this.length = length;
    }

}
