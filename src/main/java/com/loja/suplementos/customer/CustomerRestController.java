package com.loja.suplementos.customer;

import com.loja.suplementos.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService service;

    @PostMapping("/save")
    public ResponseEntity<?> createCustomer(@RequestBody Map<String, String> data) {
        try {
            this.service.save(data);
        } catch (Exception e) {
            log.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("errorMessage", e.getMessage()));
        }

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Customer customer = service.findById(id);
        if (customer != null) {
            service.delete(customer);
        }

        return "OK";
    }
}
