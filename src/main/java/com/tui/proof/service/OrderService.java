package com.tui.proof.service;

import com.tui.proof.entity.SearchOrderRequestEntity;
import com.tui.proof.exception.CookedException;
import com.tui.proof.model.Order;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order) throws NotFoundException;

    Order updateOrder(Order order) throws NotFoundException, CookedException;

    Order findById(final Long orderId) throws NotFoundException;

    List<Order> search(final SearchOrderRequestEntity searchRequest);

    void updateCookOrder(final Long orderId) throws NotFoundException;
}
