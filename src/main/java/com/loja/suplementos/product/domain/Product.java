package com.loja.suplementos.product.domain;

import com.loja.suplementos.brand.domain.Brand;
import com.loja.suplementos.nutritionaltable.domain.NutritionalTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private ProductType type;

    private BigDecimal price;

    private int quantityInStock;

    @DBRef
    private Brand brand;

    @DBRef
    private NutritionalTable nutritionalTable;
}
