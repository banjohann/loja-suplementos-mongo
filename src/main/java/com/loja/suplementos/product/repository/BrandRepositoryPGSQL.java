package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
@Profile("Postgres")
public class BrandRepositoryPGSQL implements BrandRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Brand.class, id));
    }
}
