package com.loja.suplementos.sale.dto;

import lombok.Data;

@Data
public class ClientPurchaseReportDTO {
        private String name;
        private String lastName;
        private long purchaseCount;
        private double totalPurchaseValue;

        public String getTotalPurchaseValueString() {
            return String.format("%.2f", totalPurchaseValue);
        }
}
