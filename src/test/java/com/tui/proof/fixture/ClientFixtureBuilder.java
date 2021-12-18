package com.tui.proof.fixture;

import com.tui.proof.dto.ClientDTO;
import com.tui.proof.model.Client;

import java.util.Optional;

public interface ClientFixtureBuilder {

    static Optional<Client> getOptionalClientMock() {
        return Optional.of(getClientMock());
    }

    static Client getClientMock() {
        return Client.builder()
                .clientId(1L)
                .firstName("Javier")
                .lastName("Bautista")
                .email("email@gmail.com")
                .telephone("683126520")
                .build();
    }

    static ClientDTO getClientDTOMock() {
        return ClientDTO.builder()
                .firstName("Javier")
                .lastName("Bautista")
                .email("email@gmail.com")
                .telephone("683126520")
                .build();
    }
}
