package com.loja.suplementos.payment;

import com.loja.suplementos.payment.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        paymentService.save(payment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        paymentService.update(id, payment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}