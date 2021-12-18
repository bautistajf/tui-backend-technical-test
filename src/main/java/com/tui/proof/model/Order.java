package com.tui.proof.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id", nullable = false)
    private Address deliveryAddress;

    @Column(name = "pilotes")
    private PiloteType pilotes;

    @Column(name = "order_total")
    private double orderTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "order_update_date")
    private LocalDate orderUpdateDate;

    private boolean cooked;
}
