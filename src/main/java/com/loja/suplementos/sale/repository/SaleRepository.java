package com.loja.suplementos.sale.repository;

import com.loja.suplementos.sale.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {

    Optional<Sale> findById(long id);

    List<Sale> findAll();

    void save(Sale sale);

    void update(Sale sale);

    void delete(Sale sale);
}
