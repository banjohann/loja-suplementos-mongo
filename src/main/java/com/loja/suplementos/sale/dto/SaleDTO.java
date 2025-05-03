package com.loja.suplementos.sale.dto;

import com.loja.suplementos.payment.domain.PaymentMethod;
import lombok.Data;

import java.util.List;

@Data
public class SaleDTO {
    private Long customerId;
    private Long deliveryAddressId;
    private PaymentMethod paymentMethod;
    private List<ProductQuantityDTO> products;

    @Data
    public static class ProductQuantityDTO {
        private Long productId;
        private Integer quantity;
    }
}
