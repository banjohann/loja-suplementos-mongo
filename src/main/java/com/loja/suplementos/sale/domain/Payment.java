package com.loja.suplementos.sale.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.loja.suplementos.sale.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private PaymentMethod paymentMethod;

    private PaymentStatus status = PaymentStatus.PENDING;

    private BigDecimal amount;

    private Date transactionDate;

    public Payment(PaymentDTO payment) {
        this.paymentMethod = PaymentMethod.valueOf(payment.getPaymentMethod());
        this.status = PaymentStatus.valueOf(payment.getStatus());
        this.transactionDate = new Date(System.currentTimeMillis());
    }

    public String getDescription() {
        return String.format("%s, %s", paymentMethod.getDescription(), status.getDescription());
    }
}
