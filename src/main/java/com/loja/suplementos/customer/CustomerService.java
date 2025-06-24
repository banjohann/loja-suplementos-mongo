package com.loja.suplementos.customer;

import com.loja.suplementos.customer.domain.Customer;
import com.loja.suplementos.customer.repository.CustomerRepository;
import com.loja.suplementos.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    public Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public void update(Map<String, String> params) {
        var email = params.get("email");
        var cpf = params.get("cpf");

        Customer existingCustomer = customerRepository
            .findById(params.get("id"))
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        if (!existingCustomer.getCpf().equals(cpf)) throw new IllegalArgumentException("Não é possível alterar o CPF");
        if (!existingCustomer.getEmail().equals(email)) throw new IllegalArgumentException("Não é possível alterar o email");

        existingCustomer.setName(params.get("name"));
        existingCustomer.setLastName(params.get("lastName"));
        existingCustomer.setBirthDate(Utils.convertStringToDate(params.get("birthDate")));
        existingCustomer.setPhone(params.get("phoneNumber"));

        customerRepository.save(existingCustomer);
    }

    public void delete(String customerId) {
        var customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        customerRepository.delete(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
