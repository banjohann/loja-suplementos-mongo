package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'quantityInStock' : { $gt : 0 } }")
    List<Product> findAllInStock();
}
