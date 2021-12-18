package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull
    private AddressDTO deliveryAddress;

    @NotNull
    private PiloteTypeDTO pilotes;

    @NotNull
    @Min(1)
    private Long clientId;

}
