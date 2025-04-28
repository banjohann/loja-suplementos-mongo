package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.NutritionalTable;

import java.util.List;
import java.util.Optional;

public interface NutritionalTableRepository {

    Optional<NutritionalTable> findById(Long nutritionalTableId);

    List<NutritionalTable> findAll();
}
