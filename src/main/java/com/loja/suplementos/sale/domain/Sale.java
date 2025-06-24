package com.loja.suplementos.sale.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.loja.suplementos.customer.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    private Date dateCreated = new Date();

    @DBRef
    private Customer customer;

    private Payment payment;

    private Shipping shipping;

    private Set<SaleItem> saleItems = new HashSet<>();
}
