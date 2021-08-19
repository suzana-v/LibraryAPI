package com.svulinovic.library.parser.mrz;

import lombok.Data;

@Data
public class VariableLengthData extends FixedLengthData {

    private int position;

    public VariableLengthData(int index, int length, int position) {
        super(index, length);
        this.position = position;
    }

}
