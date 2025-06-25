package com.loja.suplementos.address;

import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.customer.domain.Customer;
import com.loja.suplementos.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryAddressService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public DeliveryAddress findById(String customerId, String id) {
        return customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"))
            .getDeliveryAddresses().stream()
            .filter(address -> address.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));
    }

    public List<DeliveryAddress> findAll() {
        return customerRepository.findAll().stream().flatMap(customer -> customer.getDeliveryAddresses().stream()).collect(Collectors.toList());
    }

    public Set<DeliveryAddress> findByCustomer(String customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"))
            .getDeliveryAddresses();
    }

    public void save(String customerId, Map<String, String> params) {
        var customer = customerService.findById(customerId);

        var deliveryAddress = new DeliveryAddress(
            params.get("street"),
            params.get("number"),
            params.get("neighborhood"),
            params.get("city"),
            params.get("state"),
            params.get("zipCode")
        );

        if (customer.getDeliveryAddresses() == null) {
            customer.setDeliveryAddresses(Set.of());
        }

        customer.getDeliveryAddresses().add(deliveryAddress);
        customerRepository.save(customer);
    }

    public void update(String customerId, String id, Map<String, String> params) {
        var customer = customerService.findById(customerId);
        var deliveryAddress = customer.getDeliveryAddresses().stream()
            .filter(address -> address.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado"));

        deliveryAddress.setStreet(params.get("street"));
        deliveryAddress.setNumber(params.get("number"));
        deliveryAddress.setNeighborhood(params.get("neighborhood"));
        deliveryAddress.setCity(params.get("city"));
        deliveryAddress.setState(params.get("state"));
        deliveryAddress.setZipCode(params.get("zipCode"));

        customerRepository.save(customer);
    }

    public void delete(String addressId, String customerId) {
        Customer customer = customerService.findById(customerId);
        customer.getDeliveryAddresses().removeIf(address -> address.getId().equals(addressId));

        customerRepository.save(customer);
    }
}
