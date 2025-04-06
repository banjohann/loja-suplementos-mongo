package com.loja.suplementos.product.domain;

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
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private ProductType type;

    private String imageUrl;

    private float price;

    private int quantityInStock;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private NutritionalTable nutritionalTable;

    public Product(String name, String description, String imageUrl, float price, int quantity, Brand brand, NutritionalTable nutritionalTable) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantityInStock = quantity;
        this.brand = brand;
        this.nutritionalTable = nutritionalTable;
    }
}
