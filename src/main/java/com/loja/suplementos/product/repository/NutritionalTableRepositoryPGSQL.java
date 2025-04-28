package com.loja.suplementos.product.repository;

import com.loja.suplementos.product.domain.NutritionalTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Profile("Postgres")
public class NutritionalTableRepositoryPGSQL implements NutritionalTableRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<NutritionalTable> findById(Long id) {
        return Optional.ofNullable(entityManager.find(NutritionalTable.class, id));
    }

    @Override
    public List<NutritionalTable> findAll() {
        return entityManager.createQuery("SELECT nt FROM NutritionalTable nt", NutritionalTable.class).getResultList();
    }
}
