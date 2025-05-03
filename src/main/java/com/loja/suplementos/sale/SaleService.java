package com.loja.suplementos.sale;

import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.payment.domain.Payment;
import com.loja.suplementos.product.ProductService;
import com.loja.suplementos.sale.domain.Sale;
import com.loja.suplementos.sale.domain.SaleItem;
import com.loja.suplementos.sale.dto.SaleDTO;
import com.loja.suplementos.sale.repository.SaleRepository;
import com.loja.suplementos.shipping.domain.Shipping;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Sale findById(long id) {
        return saleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    }

    public void save(SaleDTO saleDTO) {
        var customer = customerService.findById(saleDTO.getCustomerId());
        var deliveryAddress = customer.getDeliveryAddresses().stream()
                .filter(address -> address.getId().equals(saleDTO.getDeliveryAddressId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));
        var payment = new Payment();
        var shipping = Shipping.ofNewShipping();

        var saleItems = saleDTO.getProducts().stream().map(productQuantityDTO -> {
            var product = productService.findById(productQuantityDTO.getProductId());

            return SaleItem.builder()
                .product(product)
                .quantity(productQuantityDTO.getQuantity())
                .price(product.getPrice().setScale(2))
                .build();
        }).collect(Collectors.toSet());

        Sale sale = Sale.builder()
            .customer(customer)
            .deliveryAddress(deliveryAddress)
            .payment(payment)
            .shipping(shipping)
            .saleItems(saleItems)
            .build();

        saleRepository.save(sale);
    }

    public void update(long id, SaleDTO saleDTO) {
        var sale = findById(id);
        var customer = customerService.findById(saleDTO.getCustomerId());
        var deliveryAddress = customer.getDeliveryAddresses().stream()
                .filter(address -> address.getId().equals(saleDTO.getDeliveryAddressId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));

        sale.setCustomer(customer);
        sale.setDeliveryAddress(deliveryAddress);

        var saleItems = saleDTO.getProducts().stream().map(productQuantityDTO -> {
            var product = productService.findById(productQuantityDTO.getProductId());

            return SaleItem.builder()
                .product(product)
                .quantity(productQuantityDTO.getQuantity())
                .price(product.getPrice())
                .build();
        }).collect(Collectors.toSet());

        sale.getSaleItems().clear();
        sale.getSaleItems().addAll(saleItems);

        saleRepository.save(sale);
    }

    public void delete(long id) {
        var sale = findById(id);
        saleRepository.delete(sale);
    }
}
