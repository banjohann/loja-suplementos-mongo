package com.loja.suplementos.customer.repository;

import com.loja.suplementos.customer.domain.DeliveryAddress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Profile("Postgres")
public class DeliveryAddressRepositoryPGSQL implements DeliveryAddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(DeliveryAddress deliveryAddress) {
        this.entityManager.persist(deliveryAddress);
    }

    @Override
    public void update(DeliveryAddress deliveryAddress) {
        this.entityManager.persist(deliveryAddress);
    }

    @Override
    public void delete(DeliveryAddress deliveryAddress) {
        this.entityManager.remove(deliveryAddress);
    }

    @Override
    public DeliveryAddress findById(Long id) {
        return this.entityManager.find(DeliveryAddress.class, id);
    }
}
