package com.loja.suplementos.sale.domain;

import com.loja.suplementos.product.domain.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity 
@Getter
@Table(name = "sale_item")
public class SaleItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private double price;

    public SaleItem(Long saleId, Product product, int quantity, double price) {
        this.saleId = saleId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
