package com.loja.suplementos.brand.repository;

import com.loja.suplementos.brand.domain.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
}
