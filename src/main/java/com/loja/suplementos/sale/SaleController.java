package com.loja.suplementos.sale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.customer.domain.Customer;
import com.loja.suplementos.payment.domain.PaymentMethod;
import com.loja.suplementos.product.ProductService;
import com.loja.suplementos.product.domain.ProductType;
import com.loja.suplementos.sale.domain.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private SaleService service;
    private ProductService productService;
    private CustomerService customerService;

    @GetMapping()
    public String index(Model model) {
        var sales = service.findAll();
        model.addAttribute("sales", sales);

        var prices = sales.stream()
                .collect(Collectors.toMap(
                    Sale::getId,
                    sale -> sale.getSaleItems().stream()
                        .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));
        model.addAttribute("prices", prices);

        return "sales/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var product = service.findById(id);
        var totalPrice = product.getSaleItems().stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("sale", product);
        model.addAttribute("totalPrice", totalPrice);
        return "sales/details";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) throws JsonProcessingException {
        var customers = customerService.findAll();
        var customerAddressesMap = new HashMap<Long, String>();
        for (Customer customer : customers) {
            customerAddressesMap.put(
                customer.getId(),
                new ObjectMapper().writeValueAsString(customer.getDeliveryAddresses()));
        }

        model.addAttribute("customers", customers);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("products", productService.findAllInStock());
        model.addAttribute("customerAddressesMap", customerAddressesMap);

        return "sales/new";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) throws JsonProcessingException {
        var sale = service.findById(id);
        var customers = customerService.findAll();
        var customerAddressesMap = customers.stream()
            .collect(Collectors.toMap(
                Customer::getId,
                Customer::getDeliveryAddresses
            ));

        model.addAttribute("sale", sale);
        model.addAttribute("customers", customers);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("products", productService.findAllInStock());
        model.addAttribute("customerAddressesMap", customerAddressesMap);

        return "sales/edit";
    }
}
