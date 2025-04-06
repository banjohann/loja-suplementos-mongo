package com.loja.suplementos.customer;

import com.loja.suplementos.customer.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("customers", service.findAll());

        return "customers/index";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/new";
    }

    @PostMapping("/new")
    public String createCustomer(@RequestParam Map<String, String> params, Model model) {
        try {
            this.service.save(params);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erro ao criar novo cliente: " + e.getMessage());
            return "customers/new";
        }

        return "redirect:/customers";
    }
}
