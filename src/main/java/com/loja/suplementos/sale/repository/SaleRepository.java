package com.loja.suplementos.sale.repository;

import com.loja.suplementos.sale.domain.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
}
