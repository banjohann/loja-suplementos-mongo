package com.loja.suplementos.customer.repository;

import com.loja.suplementos.customer.domain.DeliveryAddress;

public interface DeliveryAddressRepository {

    void save(DeliveryAddress deliveryAddress);

    void update(DeliveryAddress deliveryAddress);

    void delete(DeliveryAddress deliveryAddress);

    DeliveryAddress findById(Long id);
}
