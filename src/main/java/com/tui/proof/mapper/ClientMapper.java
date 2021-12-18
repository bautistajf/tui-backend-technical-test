package com.tui.proof.mapper;

import com.tui.proof.dto.ClientDTO;
import com.tui.proof.dto.ClientRequestDTO;
import com.tui.proof.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(final ClientDTO client);

    Client toEntity(final ClientRequestDTO order);

    ClientDTO toDTO(final Client order);
}
