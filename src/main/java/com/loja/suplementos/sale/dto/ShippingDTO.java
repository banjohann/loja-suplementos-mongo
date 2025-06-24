package com.loja.suplementos.sale.dto;

import lombok.Data;

@Data
public class ShippingDTO {
    private String shippingStatus;
    private String statusDescription;
    private String deliveryAddressId;
}
