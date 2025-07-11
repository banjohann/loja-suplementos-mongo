package com.loja.suplementos.sale;

import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.product.ProductService;
import com.loja.suplementos.sale.domain.PaymentMethod;
import com.loja.suplementos.sale.domain.PaymentStatus;
import com.loja.suplementos.sale.domain.Sale;
import com.loja.suplementos.sale.domain.ShippingStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
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
    public String detail(@PathVariable String id, Model model) {
        var sale = service.findById(id);

        model.addAttribute("sale", sale);
        return "sales/details";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        var customers = customerService.findAll();

        model.addAttribute("customers", customers);
        model.addAttribute("products", productService.findAllInStock());
        model.addAttribute("methods", PaymentMethod.values());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        model.addAttribute("shippingStatuses", ShippingStatus.values());

        return "sales/new";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable String id, Model model) {
        var sale = service.findById(id);
        var customers = customerService.findAll();

        model.addAttribute("sale", sale);
        model.addAttribute("customers", customers);
        model.addAttribute("products", productService.findAllInStock());
        model.addAttribute("methods", PaymentMethod.values());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        model.addAttribute("shippingStatuses", ShippingStatus.values());

        return "sales/edit";
    }
}
