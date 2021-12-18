package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull
    private String street;

    @NotNull
    private String postcode;

    @NotNull
    private String city;

    @NotNull
    private String country;
}
