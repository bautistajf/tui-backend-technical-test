package com.tui.proof.service.impl;

import com.tui.proof.model.Order;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.service.ClientService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.tui.proof.fixture.OrderFixtureBuilder.getOrderMock;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ClientService clientService;


    @Test
    void create_should_call_save() throws NotFoundException {
        when(orderRepository.save(any())).thenReturn(getOrderMock());
        final Order result = service.createOrder(getOrderMock());

        verify(orderRepository).save(any());
        assertNotNull(result);
    }

}
