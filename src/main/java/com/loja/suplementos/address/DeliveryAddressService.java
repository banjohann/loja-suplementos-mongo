package com.loja.suplementos.address;

import com.loja.suplementos.address.repository.DeliveryAddressRepository;
import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryAddressService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public List<DeliveryAddress> findAll() {
        return deliveryAddressRepository.findAll();
    }

    public DeliveryAddress findById(Long id) {
        return deliveryAddressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço de entrega não encontrado com o ID: " + id));
    }

    public void save(Map<String, String> params) {
        var customer = customerService.findById(Long.parseLong(params.get("customerId")));

        var deliveryAddress = new DeliveryAddress(
            params.get("street"),
            params.get("number"),
            params.get("neighborhood"),
            params.get("city"),
            params.get("state"),
            params.get("zipCode"),
            customer.getId()
        );

        customer.addDeliveryAddress(deliveryAddress);

        customerRepository.save(customer);
        deliveryAddressRepository.save(deliveryAddress);
    }

    public void update(Long id, Map<String, String> params) {
        var customer = customerService.findById(Long.parseLong(params.get("customerId")));
        var deliveryAddress = findById(id);
        var oldCustomer = customerService.findById(deliveryAddress.getCustomerId());

        deliveryAddress.setStreet(params.get("street"));
        deliveryAddress.setNumber(params.get("number"));
        deliveryAddress.setNeighborhood(params.get("neighborhood"));
        deliveryAddress.setCity(params.get("city"));
        deliveryAddress.setState(params.get("state"));
        deliveryAddress.setZipCode(params.get("zipCode"));
        deliveryAddress.setCustomerId(customer.getId());

        if (oldCustomer.getId() != customer.getId()) {
            oldCustomer
                .getDeliveryAddresses()
                .removeIf(address -> id.equals(deliveryAddress.getId()));
            customer.addDeliveryAddress(deliveryAddress);
            customerRepository.save(customer);
        }

        deliveryAddressRepository.save(deliveryAddress);
    }

    public void delete(Long addressId) {
        var deliveryAddress = findById(addressId);
        var customer = customerRepository.findById(deliveryAddress.getCustomerId());

        if (customer.isPresent()) {
            customer.get().getDeliveryAddresses().remove(deliveryAddress);
            customerRepository.save(customer.get());
        }

        deliveryAddressRepository.delete(deliveryAddress);
    }
}
