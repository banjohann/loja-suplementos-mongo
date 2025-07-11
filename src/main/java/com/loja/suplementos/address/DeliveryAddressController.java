package com.loja.suplementos.address;

import com.loja.suplementos.customer.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/address")
public class DeliveryAddressController {

    private final DeliveryAddressService service;
    private final CustomerService customerService;

    @GetMapping("/new/{customerId}")
    public String create(@PathVariable String customerId, Model model) {
        model.addAttribute("customer", customerService.findById(customerId));

        return "addresses/new";
    }

    @GetMapping("/edit/{customerId}/{id}")
    public String edit(@PathVariable String customerId, @PathVariable String id, Model model) {
        var deliveryAddress = service.findById(customerId, id);
        model.addAttribute("address", deliveryAddress);
        model.addAttribute("customer", customerService.findById(customerId));

        return "addresses/edit";
    }
}
