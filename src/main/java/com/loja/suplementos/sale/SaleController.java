package com.loja.suplementos.sale;

import com.loja.suplementos.customer.CustomerService;
import com.loja.suplementos.product.ProductService;
import com.loja.suplementos.product.domain.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class SaleController {

    private SaleService service;
    private ProductService productService;
    private CustomerService customerService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("sales", service.findAll());

        return "sales/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var product = service.findById(id);
        model.addAttribute("sale", product);
        return "sales/details";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return "sales/new";
    }

    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        var product = service.findById(id);
        model.addAttribute("sale", product);
        return "sales/edit";
    }
}
