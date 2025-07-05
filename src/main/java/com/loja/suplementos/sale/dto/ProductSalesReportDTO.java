package com.loja.suplementos.sale.dto;

public class ProductSalesReportDTO {
    private String productName;
    private int totalSales;
    private double totalValue;

    public ProductSalesReportDTO(String productName) {
        this.productName = productName;
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getTotalSales() { return totalSales; }
    public void setTotalSales(int totalSales) { this.totalSales = totalSales; }

    public String getTotalValue() {
        return String.format("%.2f", totalValue);
    }

    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }

    public void addSales(int quantity, double value) {
        this.totalSales += quantity;
        this.totalValue += value;
    }
}
