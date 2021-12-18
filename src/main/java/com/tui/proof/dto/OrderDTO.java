package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    @NotNull
    private AddressDTO deliveryAddress;

    @NotNull
    private PiloteTypeDTO pilotes;

    @NotNull
    private ClientDTO client;

    private LocalDate orderDate;

    private double orderTotal;
}
