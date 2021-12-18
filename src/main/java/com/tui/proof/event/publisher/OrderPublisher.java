package com.tui.proof.event.publisher;

import com.tui.proof.configuration.properties.OrderProperties;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderPublisher {

    private final ApplicationEventPublisher publisher;
    private final OrderService orderService;
    private final OrderProperties orderProperties;

    @Async
    public void publish(final Order order) throws InterruptedException, NotFoundException {
        log.info("Waiting to Produce order: " + order);
        TimeUnit.SECONDS.sleep(orderProperties.getWaitToTimeInSeconds());
        log.info("Order id to cook: " + order);
        final Order orderDB = orderService.findById(order.getOrderId());
        log.info("Publish order to cook: " + order);
        publisher.publishEvent(orderDB);
    }
}
