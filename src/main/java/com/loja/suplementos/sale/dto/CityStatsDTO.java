package com.loja.suplementos.sale.dto;

import lombok.Getter;

@Getter
public class CityStatsDTO {
    private final String city;
    private int saleCount = 0;
    private Double totalValue = 0.0;

    public CityStatsDTO(String city) {
        this.city = city;
    }

    public void addSale(double value) {
        saleCount++;
        totalValue += value;
    }
}
