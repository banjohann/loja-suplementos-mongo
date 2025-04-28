package com.loja.suplementos.product;

import com.loja.suplementos.product.domain.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;



    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", service.findAll());

        return "products/index";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("brands", service.getAllBrands());
        model.addAttribute("nutritionalTables", service.getAllNutritionalTables());
        model.addAttribute("productTypes", Arrays.stream(ProductType.values()).map(ProductType::name).toList());
        return "products/new";
    }
}
