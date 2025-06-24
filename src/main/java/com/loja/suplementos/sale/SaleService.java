package com.loja.suplementos.sale;

import com.loja.suplementos.address.DeliveryAddress;
import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.product.ProductService;
import com.loja.suplementos.sale.domain.Payment;
import com.loja.suplementos.sale.domain.PaymentMethod;
import com.loja.suplementos.sale.domain.PaymentStatus;
import com.loja.suplementos.sale.domain.Sale;
import com.loja.suplementos.sale.domain.SaleItem;
import com.loja.suplementos.sale.domain.Shipping;
import com.loja.suplementos.sale.domain.ShippingStatus;
import com.loja.suplementos.sale.dto.SaleDTO;
import com.loja.suplementos.sale.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(String id) {
        return saleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    }

    public void save(SaleDTO saleDTO) {
        var customer = customerService.findById(saleDTO.getCustomerId());
        var deliveryAddress = customer.getDeliveryAddresses().stream()
            .filter(address -> address.getId().equals(saleDTO.getShipping().getDeliveryAddressId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));

        var shipping = new Shipping(saleDTO.getShipping(), deliveryAddress.clone());
        var payment = new Payment(saleDTO.getPayment());

        var saleItems = saleDTO.getProducts().stream().map(productQuantityDTO -> {
            var product = productService.findById(productQuantityDTO.getProductId());

            return SaleItem.builder()
                .product(product)
                .quantity(productQuantityDTO.getQuantity())
                .price(product.getPrice().setScale(2))
                .build();
        }).collect(Collectors.toSet());

        var totalAmount = saleItems.stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        payment.setAmount(totalAmount);

        Sale sale = Sale.builder()
            .customer(customer)
            .dateCreated(new Date())
            .payment(payment)
            .shipping(shipping)
            .saleItems(saleItems)
            .build();

        saleRepository.save(sale);
    }

    public void update(String id, SaleDTO saleDTO) {
        var sale = findById(id);
        var customer = customerService.findById(saleDTO.getCustomerId());
        var deliveryAddress = customer.getDeliveryAddresses().stream()
            .filter(address -> address.getId().equals(saleDTO.getShipping().getDeliveryAddressId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));
        var payment = sale.getPayment();
        var shipping = sale.getShipping();

        var saleItems = saleDTO.getProducts().stream().map(productQuantityDTO -> {
            var product = productService.findById(productQuantityDTO.getProductId());

            return SaleItem.builder()
                .product(product)
                .quantity(productQuantityDTO.getQuantity())
                .price(product.getPrice())
                .build();
        }).collect(Collectors.toSet());

        var totalAmount = saleItems.stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        payment.setAmount(totalAmount);
        payment.setPaymentMethod(PaymentMethod.valueOf(saleDTO.getPayment().getPaymentMethod()));
        payment.setStatus(PaymentStatus.valueOf(saleDTO.getPayment().getStatus()));

        shipping.setDeliveryAddress(deliveryAddress.clone());
        shipping.setStatus(ShippingStatus.valueOf(saleDTO.getShipping().getShippingStatus()));
        shipping.setStatusDescription(saleDTO.getShipping().getStatusDescription());

        sale.getSaleItems().clear();
        sale.getSaleItems().addAll(saleItems);

        saleRepository.save(sale);
    }

    public void delete(String id) {
        var sale = findById(id);
        saleRepository.delete(sale);
    }
}
