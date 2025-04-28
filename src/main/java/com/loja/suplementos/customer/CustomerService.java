package com.loja.suplementos.customer;

import com.loja.suplementos.customer.domain.Customer;
import com.loja.suplementos.customer.domain.DeliveryAddress;
import com.loja.suplementos.customer.repository.CustomerRepository;
import com.loja.suplementos.customer.repository.DeliveryAddressRepository;
import com.loja.suplementos.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public CustomerService(CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public void save(Map<String, String> params) {
        var email = params.get("email");
        var cpf = params.get("cpf");

        Customer existingCustomer = customerRepository.findByEmailOrCpf(email, cpf);
        if (existingCustomer != null) {
            if (existingCustomer.getCpf().equals(cpf)) throw new IllegalArgumentException("CPF já cadastrado");

            if (existingCustomer.getEmail().equals(email)) throw new IllegalArgumentException("Email já cadastrado");
        }

        Customer customer = Customer
            .builder()
            .name(params.get("name"))
            .lastName(params.get("lastName"))
            .email(params.get("email"))
            .phone(params.get("phoneNumber"))
            .birthDate(Utils.convertStringToDate(params.get("birthDate")))
            .cpf(params.get("cpf"))
            .build();

        customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public void update(Map<String, String> params) {
        var email = params.get("email");
        var cpf = params.get("cpf");

        Customer existingCustomer = customerRepository
            .findById(Long.valueOf(params.get("id")))
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        if (!existingCustomer.getCpf().equals(cpf)) throw new IllegalArgumentException("Não é possível alterar o CPF");
        if (!existingCustomer.getEmail().equals(email)) throw new IllegalArgumentException("Não é possível alterar o email");

        existingCustomer.setName(params.get("name"));
        existingCustomer.setLastName(params.get("lastName"));
        existingCustomer.setBirthDate(Utils.convertStringToDate(params.get("birthDate")));
        existingCustomer.setPhone(params.get("phoneNumber"));

        customerRepository.update(existingCustomer);
    }

    public void delete(Long customerId) {
        var customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        customerRepository.delete(customer);
    }

    public void addDeliveryAddress(Long customerId, Map<String, String> params) {
        var customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        var deliveryAddress = new DeliveryAddress(
            params.get("street"),
            params.get("number"),
            params.get("neighborhood"),
            params.get("city"),
            params.get("state"),
            params.get("zipCode"),
            customerId
        );

        customer.addDeliveryAddress(deliveryAddress);

        deliveryAddressRepository.save(deliveryAddress);
        customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void deleteDeliveryAddress(Long addressId) {
        var deliveryAddress = deliveryAddressRepository.findById(addressId);
        if (deliveryAddress == null) throw new IllegalArgumentException("Endereço não encontrado");

        deliveryAddressRepository.delete(deliveryAddress);
    }

    public void editDeliveryAddress(Long customerId, Map<String, String> data) {
        customerRepository
            .findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        var deliveryAddress = deliveryAddressRepository.findById(Long.valueOf(data.get("addressId")));
        if (deliveryAddress == null) throw new IllegalArgumentException("Endereço não encontrado");

        deliveryAddress.setStreet(data.get("street"));
        deliveryAddress.setNumber(data.get("number"));
        deliveryAddress.setNeighborhood(data.get("neighborhood"));
        deliveryAddress.setCity(data.get("city"));
        deliveryAddress.setState(data.get("state"));
        deliveryAddress.setZipCode(data.get("zipCode"));

        deliveryAddressRepository.save(deliveryAddress);
    }
}
