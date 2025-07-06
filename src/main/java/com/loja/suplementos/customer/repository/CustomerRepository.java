package com.loja.suplementos.customer.repository;

import com.loja.suplementos.customer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByEmailOrCpf(String email, String cpf);
}
