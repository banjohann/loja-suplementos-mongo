package com.loja.suplementos.nutritionaltable.repository;

import com.loja.suplementos.nutritionaltable.domain.NutritionalTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalTableRepository extends MongoRepository<NutritionalTable, String> {
}
