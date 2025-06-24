package com.loja.suplementos.nutritionaltable.repository;

import com.loja.suplementos.nutritionaltable.domain.NutritionalTable;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongo")
public interface NutritionalTableRepository extends MongoRepository<NutritionalTable, String> {
}
