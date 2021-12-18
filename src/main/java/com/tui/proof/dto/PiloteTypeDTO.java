package com.tui.proof.dto;

public enum PiloteTypeDTO {

    FIVE(5),
    TEN(10),
    FIFTEEN(15);

    private int numVal;

    PiloteTypeDTO(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
