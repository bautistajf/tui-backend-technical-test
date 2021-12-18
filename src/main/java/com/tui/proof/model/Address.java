package com.tui.proof.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "post_code")
    private String postcode;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "country")
    private String country;
}
