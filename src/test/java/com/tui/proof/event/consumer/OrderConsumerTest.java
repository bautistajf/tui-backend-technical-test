package com.tui.proof.event.consumer;

import com.tui.proof.configuration.properties.OrderProperties;
import com.tui.proof.service.OrderService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.TimeUnit;

import static com.tui.proof.fixture.OrderFixtureBuilder.getOrderMock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    @InjectMocks
    private OrderConsumer consumer;

    @Mock
    private OrderProperties orderProperties;

    @Mock
    private OrderService orderService;

    @Test
    void cookOrder_should_call_async_method() throws NotFoundException, InterruptedException {
        when(orderProperties.getCookTimeInSeconds()).thenReturn(2);
        doNothing().when(orderService).updateCookOrder(1L);

        consumer.cookOrder(getOrderMock());
        TimeUnit.SECONDS.sleep(3);

        verify(orderProperties).getCookTimeInSeconds();
        verify(orderService).updateCookOrder(1L);
    }
}
