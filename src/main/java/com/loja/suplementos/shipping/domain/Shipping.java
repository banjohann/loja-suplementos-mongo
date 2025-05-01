package com.loja.suplementos.shipping.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    private String statusDescription;

    public static Shipping ofNewShipping() {
        var trackingNumber = UUID.randomUUID().toString();
        var status = ShippingStatus.NOT_SHIPPED;
        var statusDescription = "Pedido registrado";

        return Shipping.builder().trackingNumber(trackingNumber).status(status).statusDescription(statusDescription).build();
    }
}