package com.tui.proof.repository;

import com.tui.proof.entity.SearchOrderRequestEntity;
import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;


public final class SpecificationOrder {

    private SpecificationOrder() {
    }

    public static Specification<Order> getOrderSpecification(
            final SearchOrderRequestEntity searchRequest) {
        return (order, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (nonNull(searchRequest)) {

                if (nonNull(searchRequest.getOrderId())) {
                    predicates.add(cb.equal(order.get("orderId"), searchRequest.getOrderId()));
                }

                if (nonNull(searchRequest.getDeliveryAddress())) {
                    setAddressSpecification(cb, searchRequest, order, predicates);
                }

                if (nonNull(searchRequest.getClient())) {
                    setClientSpecification(cb, searchRequest, order, predicates);
                }
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

    private static void setAddressSpecification(final CriteriaBuilder cb,
                                                final SearchOrderRequestEntity searchRequest,
                                                final Root<Order> order,
                                                final List<Predicate> predicates) {

        final Join<Order, Address> joinAddress = order.join("deliveryAddress");

        if (nonNull(searchRequest.getDeliveryAddress().getCity())) {
            predicates.add(cb.like(joinAddress.get("city"), "%" + searchRequest.getDeliveryAddress().getCity() + "%"));
        }
        if (nonNull(searchRequest.getDeliveryAddress().getCountry())) {
            predicates.add(cb.like(joinAddress.get("country"), "%" + searchRequest.getDeliveryAddress().getCountry() + "%"));
        }
        if (nonNull(searchRequest.getDeliveryAddress().getPostcode())) {
            predicates.add(cb.equal(joinAddress.get("postCode"), searchRequest.getDeliveryAddress().getPostcode()));
        }
        if (nonNull(searchRequest.getDeliveryAddress().getStreet())) {
            predicates.add(cb.like(joinAddress.get("street"), "%" + searchRequest.getDeliveryAddress().getStreet() + "%"));
        }
    }

    private static void setClientSpecification(final CriteriaBuilder cb,
                                               final SearchOrderRequestEntity searchRequest,
                                               final Root<Order> order,
                                               final List<Predicate> predicates) {

        final Join<Order, Client> joinClient = order.join("client");

        if (nonNull(searchRequest.getClient().getFirstName())) {
            predicates.add(cb.like(joinClient.get("firstName"), "%" + searchRequest.getClient().getFirstName() + "%"));
        }
        if (nonNull(searchRequest.getClient().getLastName())) {
            predicates.add(cb.like(joinClient.get("lastName"), "%" + searchRequest.getClient().getLastName() + "%"));
        }
        if (nonNull(searchRequest.getClient().getTelephone())) {
            predicates.add(cb.like(joinClient.get("telephone"), "%" + searchRequest.getClient().getTelephone() + "%"));
        }
        if (nonNull(searchRequest.getClient().getEmail())) {
            predicates.add(cb.like(joinClient.get("email"), "%" + searchRequest.getClient().getEmail() + "%"));
        }

    }
}
