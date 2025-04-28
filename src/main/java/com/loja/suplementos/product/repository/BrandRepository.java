package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {

    Optional<Brand> findById(Long id);

    List<Brand> findAll();
}
