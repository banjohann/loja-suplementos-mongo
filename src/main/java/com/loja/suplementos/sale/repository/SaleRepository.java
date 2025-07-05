package com.loja.suplementos.sale.repository;

import com.loja.suplementos.sale.domain.Sale;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("mongo")
public interface SaleRepository extends MongoRepository<Sale, String> {
}
