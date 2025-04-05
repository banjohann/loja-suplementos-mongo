package com.loja.suplementos.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "nutritional_table")
public class NutritionalTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private float servingSize;

    private int calories;

    private double fatPerServing;

    private double carbsPerServing;

    private double proteinPerServing;

    public NutritionalTable(float servingSize, int calories, double fatPerServing, double carbsPerServing, double proteinPerServing) {
        this.servingSize = servingSize;
        this.calories = calories;
        this.fatPerServing = fatPerServing;
        this.carbsPerServing = carbsPerServing;
        this.proteinPerServing = proteinPerServing;
    }
}
