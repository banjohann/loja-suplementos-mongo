package com.loja.suplementos.sale.domain;

import com.loja.suplementos.product.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleItem {

    private String id = UUID.randomUUID().toString();

    @DBRef
    private Product product;

    private int quantity;

    private BigDecimal price;
}
