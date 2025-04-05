package com.loja.suplementos.shipping.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    private String statusDescription;

    public Shipping(String trackingNumber, String statusDescription) {
        this.trackingNumber = trackingNumber;
        this.status = ShippingStatus.NOT_SHIPPED;
        this.statusDescription = statusDescription;
    }
}