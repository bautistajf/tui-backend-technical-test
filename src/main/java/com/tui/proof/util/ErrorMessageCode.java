package com.tui.proof.util;

@SuppressWarnings("java:S1845")
public enum ErrorMessageCode {

    ERROR_CLIENT_DOES_NOT_EXIST_001("Client with id: %s doesn't exist"),
    ERROR_ORDER_DOES_NOT_EXIST_001("Order with id: %s doesn't exist"),
    ERROR_ORDER_COOKED_001("Order has been cooked"),
    ERROR_INVALID_CREDENTIAL("Invalid credentials");

    public final String name;

    ErrorMessageCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

