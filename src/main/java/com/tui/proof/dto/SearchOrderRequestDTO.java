package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrderRequestDTO {

    private Long orderId;

    private ClientDTO client;

    private AddressDTO deliveryAddress;

}
