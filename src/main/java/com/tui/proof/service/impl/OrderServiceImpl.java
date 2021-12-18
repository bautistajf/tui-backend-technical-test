package com.tui.proof.service.impl;

import com.tui.proof.entity.SearchOrderRequestEntity;
import com.tui.proof.exception.CookedException;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;
import com.tui.proof.repository.OrderRepository;
import com.tui.proof.repository.SpecificationOrder;
import com.tui.proof.service.ClientService;
import com.tui.proof.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tui.proof.util.ErrorMessageCode.ERROR_ORDER_COOKED_001;
import static com.tui.proof.util.ErrorMessageCode.ERROR_ORDER_DOES_NOT_EXIST_001;
import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    private static final double PRICE_BY_PILOTE = 3;

    @Override
    public Order createOrder(final Order order) throws NotFoundException {
        final Client client = clientService.findById(order.getClient().getClientId());
        order.setClient(client);

        order.setOrderTotal(PRICE_BY_PILOTE * order.getPilotes().getNumVal());
        order.setOrderDate(LocalDate.now());

        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(final Order order) throws NotFoundException, CookedException {
        final Client client = clientService.findById(order.getClient().getClientId());
        order.setClient(client);
        if (nonNull(findById(order.getOrderId()))) {

            if (order.isCooked())
                throw new CookedException(format(ERROR_ORDER_COOKED_001.getName(), order.getOrderId()));

            order.setOrderTotal(PRICE_BY_PILOTE * order.getPilotes().getNumVal());
            order.setOrderUpdateDate(LocalDate.now());

            orderRepository.save(order);
        }
        return order;
    }

    public void updateCookOrder(final Long orderId) throws NotFoundException {

        boolean exist = orderRepository.existsById(orderId);
        if (!exist) {
            throw new NotFoundException(format(ERROR_ORDER_DOES_NOT_EXIST_001.getName(), orderId));
        }
        final Order order = findById(orderId);
        order.setCooked(true);
        orderRepository.save(order);
    }

    @Override
    public Order findById(final Long orderId) throws NotFoundException {
        final Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new NotFoundException(format(ERROR_ORDER_DOES_NOT_EXIST_001.getName(), orderId));
        }

        return order.get();
    }

    @Override
    public List<Order> search(final SearchOrderRequestEntity searchRequest) {
        return orderRepository.findAll(SpecificationOrder.getOrderSpecification(searchRequest));
    }
}
