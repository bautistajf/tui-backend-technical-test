package com.tui.proof.fixture;

import com.tui.proof.dto.AddressDTO;
import com.tui.proof.dto.OrderRequestDTO;
import com.tui.proof.dto.PiloteTypeDTO;
import com.tui.proof.dto.SearchOrderRequestDTO;
import com.tui.proof.model.Address;
import com.tui.proof.model.Order;
import com.tui.proof.model.PiloteType;

import java.time.LocalDate;
import java.util.Optional;

import static com.tui.proof.fixture.ClientFixtureBuilder.getClientMock;

public interface OrderFixtureBuilder {

    static Optional<Order> getOptionalOrderMock() {
        return Optional.of(getOrderMock());
    }

    static Order getOrderMock() {
        return Order.builder()
                .orderId(1L)
                .orderDate(LocalDate.now())
                .client(getClientMock())
                .pilotes(PiloteType.TEN)
                .deliveryAddress(getAddressMock())
                .build();
    }

    static OrderRequestDTO getRequestOrderMock() {
        return OrderRequestDTO.builder()
                .clientId(1L)
                .pilotes(PiloteTypeDTO.TEN)
                .deliveryAddress(getAddressDTOMock())
                .build();
    }

    static AddressDTO getAddressDTOMock() {
        return AddressDTO.builder()
                .postcode("07013")
                .city("Mallorca")
                .country("Spain")
                .street("Jaume III")
                .build();
    }

    static Address getAddressMock() {
        return Address.builder()
                .addressId(1L)
                .postcode("07013")
                .city("Mallorca")
                .country("Spain")
                .street("Jaume III")
                .build();
    }

    static SearchOrderRequestDTO getSearchOrderRequestDTOMock() {
        return SearchOrderRequestDTO.builder()
                .orderId(1L)
                .build();
    }
}
