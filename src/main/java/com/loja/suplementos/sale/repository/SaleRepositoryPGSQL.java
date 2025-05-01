package com.loja.suplementos.sale.repository;

import com.loja.suplementos.sale.domain.Sale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Profile("Postgres")
public class SaleRepositoryPGSQL implements SaleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Sale> findById(long id) {
        Sale sale = entityManager.find(Sale.class, id);
        return Optional.ofNullable(sale);
    }

    @Override
    public List<Sale> findAll() {
        return entityManager.createQuery("SELECT s FROM Sale s", Sale.class).getResultList();
    }

    @Override
    public void save(Sale sale) {
        entityManager.persist(sale);
    }

    @Override
    public void update(Sale sale) {
        entityManager.merge(sale);
    }

    @Override
    public void delete(Sale sale) {
        entityManager.remove(entityManager.contains(sale) ? sale : entityManager.merge(sale));
    }
}
