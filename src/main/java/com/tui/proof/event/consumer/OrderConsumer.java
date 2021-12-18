package com.tui.proof.event.consumer;

import com.tui.proof.configuration.properties.OrderProperties;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderProperties orderProperties;
    private final OrderService orderService;

    @EventListener
    public void cookOrder(final Order order) {

        log.info("CONSUMER: A order has been created to cook: " + order);
        // We simulate an async call to cook
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(orderProperties.getCookTimeInSeconds());
                orderService.updateCookOrder(order.getOrderId());
                log.info("COOK: order: " + order);
            } catch (InterruptedException | NotFoundException e) {
                log.error(e);
                Thread.currentThread().interrupt();
            }
        });

    }
}
