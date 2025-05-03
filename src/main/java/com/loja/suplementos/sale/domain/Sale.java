package com.loja.suplementos.sale.domain;

import java.util.HashSet;
import java.util.Set;

import com.loja.suplementos.customer.domain.Customer;
import com.loja.suplementos.customer.domain.DeliveryAddress;
import com.loja.suplementos.payment.domain.Payment;
import com.loja.suplementos.shipping.domain.Shipping;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "saleId")
    private Set<SaleItem> saleItems = new HashSet<>();

    public void addSaleItem(SaleItem saleItem) {
        this.saleItems.add(saleItem);
    }

    public void removeSaleItem(SaleItem saleItem) {
        this.saleItems.removeIf(item -> item.getId().equals(saleItem.getId()));
    }
}
