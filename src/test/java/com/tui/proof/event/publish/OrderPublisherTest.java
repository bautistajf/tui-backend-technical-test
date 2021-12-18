package com.tui.proof.event.publish;

import com.tui.proof.configuration.properties.OrderProperties;
import com.tui.proof.event.publisher.OrderPublisher;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static com.tui.proof.fixture.OrderFixtureBuilder.getOrderMock;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderPublisherTest {

    @InjectMocks
    private OrderPublisher publisher;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderProperties orderProperties;


    @Test
    void publish_should_call_publishEvent() throws NotFoundException, InterruptedException {
        when(orderService.findById(anyLong())).thenReturn(getOrderMock());
        when(orderProperties.getWaitToTimeInSeconds()).thenReturn(2);
        doNothing().when(applicationEventPublisher).publishEvent(getOrderMock());

        final Order order = getOrderMock();

        publisher.publish(order);

        verify(orderService).findById(anyLong());
        verify(orderProperties).getWaitToTimeInSeconds();
        verify(applicationEventPublisher).publishEvent(getOrderMock());
    }
}
