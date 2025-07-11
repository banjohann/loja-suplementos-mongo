package com.loja.suplementos.address;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class DeliveryAddressRestController {

    private final DeliveryAddressService service;

    @PostMapping("/{customerId}")
    public ResponseEntity<?> save(@PathVariable String customerId, @RequestBody Map<String, String> data) {
        try {
            service.save(customerId, data);
        } catch (Exception e) {
            log.error("Erro ao salvar endereço", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", e.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{customerId}/{id}")
    public ResponseEntity<?> update(@PathVariable String customerId, @PathVariable String id, @RequestBody Map<String, String> data) {
        try {
            service.update(customerId, id, data);
        } catch (Exception e) {
            log.error("Erro ao salvar endereço", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", e.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{customerId}/{addressId}")
    public ResponseEntity<?> delete(@PathVariable String customerId, @PathVariable String addressId) {
        try {
            this.service.delete(customerId, addressId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", e.getMessage()));
        }

        return ResponseEntity.ok().build();
    }
}
