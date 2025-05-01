package com.loja.suplementos.sale;

import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.payment.domain.Payment;
import com.loja.suplementos.sale.domain.Sale;
import com.loja.suplementos.sale.repository.SaleRepository;
import com.loja.suplementos.shipping.domain.Shipping;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerService customerService;

    public SaleService(SaleRepository saleRepository, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.customerService = customerService;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(long id) {
        return saleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    }

    public void save(Map<String, String> data) {
        var customer = customerService.findById(Long.parseLong(data.get("customerId")));
        var deliveryAddress = customer.getDeliveryAddresses().stream()
                .filter(address -> address.getId() == Long.parseLong(data.get("deliveryAddressId")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));
        var payment = new Payment();
        var shipping = Shipping.ofNewShipping();

        Sale sale = Sale.builder()
            .customer(customer)
            .deliveryAddress(deliveryAddress)
            .payment(payment)
            .shipping(shipping)
            .build();

        saleRepository.save(sale);
    }

    public void update(long id, Map<String, String> data) {
        var sale = findById(id);
        var customer = customerService.findById(Long.parseLong(data.get("customerId")));
        var deliveryAddress = customer.getDeliveryAddresses().stream()
                .filter(address -> address.getId() == Long.parseLong(data.get("deliveryAddressId")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));

        sale.setCustomer(customer);
        sale.setDeliveryAddress(deliveryAddress);

        saleRepository.save(sale);
    }

    public void delete(long id) {
        var sale = findById(id);

        saleRepository.delete(sale);
    }
}
