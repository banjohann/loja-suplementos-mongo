package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);

    void update(Product product);

    void delete(Product product);

    List<Product> findAll();
}
