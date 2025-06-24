package com.loja.suplementos.sale.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    private String paymentMethod;
    private String status;
    private Double amount;
    private Date transactionDate;
}
