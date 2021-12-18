package com.tui.proof.entity;

import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import lombok.Data;

@Data
public class SearchOrderRequestEntity {

    private Long orderId;

    private Client client;

    private Address deliveryAddress;
}
