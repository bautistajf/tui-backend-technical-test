package com.tui.proof.util;

import lombok.Value;

import java.util.List;

@Value
public class ErrorResponse {
    List<ErrorDescription> errors;
}
