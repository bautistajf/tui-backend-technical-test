package com.tui.proof.mapper;

import com.tui.proof.dto.OrderDTO;
import com.tui.proof.dto.OrderRequestDTO;
import com.tui.proof.dto.SearchOrderRequestDTO;
import com.tui.proof.entity.SearchOrderRequestEntity;
import com.tui.proof.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(final OrderDTO order);

    @Mapping(source = "clientId", target = "client.clientId")
    Order toEntity(final OrderRequestDTO order);

    OrderDTO toDTO(final Order order);

    List<OrderDTO> toListDTO(final List<Order> orders);

    SearchOrderRequestEntity toEntity(final SearchOrderRequestDTO search);

}
