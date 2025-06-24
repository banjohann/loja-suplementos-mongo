package com.loja.suplementos.sale.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SaleDTO {
    private String customerId;
    private PaymentDTO payment;
    private ShippingDTO shipping;
    private List<ProductQuantityDTO> products;

    @Data
    public static class ProductQuantityDTO {
        private String productId;
        private Integer quantity;
    }
}
