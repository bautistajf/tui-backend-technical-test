package com.tui.proof.service.impl;

import com.tui.proof.exception.CookedException;
import com.tui.proof.model.Order;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.service.ClientService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.tui.proof.fixture.ClientFixtureBuilder.getClientMock;
import static com.tui.proof.fixture.OrderFixtureBuilder.getOptionalOrderMock;
import static com.tui.proof.fixture.OrderFixtureBuilder.getOrderMock;
import static com.tui.proof.util.ErrorMessageCode.ERROR_ORDER_DOES_NOT_EXIST_001;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void updateOrder_should_return_exception() throws NotFoundException {

        final Optional<Order> optionalOrder = getOptionalOrderMock();
        optionalOrder.get().setCooked(true);

        when(clientService.findById(1L)).thenReturn(getClientMock());
        when(orderRepository.findById(1L)).thenReturn(optionalOrder);

        final CookedException ex = assertThrows(CookedException.class, () ->
                service.updateOrder(getOrderMock()));

        verify(clientService).findById(1L);
        verify(orderRepository).findById(1L);
    }

    @Test
    void updateCookOrder_should_return() throws NotFoundException {

        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.findById(1L)).thenReturn(getOptionalOrderMock());
        when(orderRepository.save(any())).thenReturn(getOrderMock());

        service.updateCookOrder(1L);
        verify(orderRepository).existsById(1L);
        verify(orderRepository).findById(1L);
        verify(orderRepository).save(any());
    }

    @Test
    void updateCookOrder_should_return_exception() {

        when(orderRepository.existsById(1L)).thenReturn(false);

        final NotFoundException ex = assertThrows(NotFoundException.class, () ->
                service.updateCookOrder(1L));
        verify(orderRepository).existsById(1L);
        assertEquals(format(ERROR_ORDER_DOES_NOT_EXIST_001.getName(),
                        1),
                ex.getMessage());
    }

    @Test
    void findById_should_return_exception() {

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        final NotFoundException ex = assertThrows(NotFoundException.class, () ->
                service.findById(1L));
        verify(orderRepository).findById(1L);
        assertEquals(format(ERROR_ORDER_DOES_NOT_EXIST_001.getName(),
                        1),
                ex.getMessage());
    }

    @Test
    void findById_should_return_data() throws NotFoundException {

        when(orderRepository.findById(1L)).thenReturn(getOptionalOrderMock());

        final Order result = service.findById(1L);
        verify(orderRepository).findById(1L);
        assertEquals(getOptionalOrderMock().get(), result);
    }

}
