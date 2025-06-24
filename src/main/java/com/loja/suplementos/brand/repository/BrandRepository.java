package com.loja.suplementos.brand.repository;

import com.loja.suplementos.brand.domain.Brand;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongo")
public interface BrandRepository extends MongoRepository<Brand, String> {
}
