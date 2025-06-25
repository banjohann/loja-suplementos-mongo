package com.loja.suplementos.sale.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String paymentMethod;
    private String status;
}
