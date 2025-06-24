package com.loja.suplementos.sale.domain;

import com.loja.suplementos.address.DeliveryAddress;
import com.loja.suplementos.sale.dto.ShippingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    private String trackingNumber;

    private ShippingStatus status;

    private String statusDescription;

    private DeliveryAddress deliveryAddress;

    public Shipping(ShippingDTO shipping, DeliveryAddress deliveryAddress) {
        this.trackingNumber = UUID.randomUUID().toString();
        this.status = ShippingStatus.NOT_SHIPPED;
        this.statusDescription = shipping.getShippingStatus();
        this.deliveryAddress = deliveryAddress;
    }
}