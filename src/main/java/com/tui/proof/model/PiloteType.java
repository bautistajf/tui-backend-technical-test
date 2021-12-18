package com.tui.proof.model;

public enum PiloteType {
    FIVE(5),
    TEN(10),
    FIFTEEN(15);

    private int numVal;

    PiloteType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
