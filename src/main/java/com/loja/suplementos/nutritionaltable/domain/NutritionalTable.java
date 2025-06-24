package com.loja.suplementos.nutritionaltable.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nutritional_tables")
public class NutritionalTable {

    @Id
    private String id;

    private float servingSize;

    private int calories;

    private double fatPerServing;

    private double carbsPerServing;

    private double proteinPerServing;

    private String description;
}
