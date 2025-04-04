package com.ranking.suplementos.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public abstract class Supplement {
    @Id
    private Long id;

    private String name;

    private String brand;

    private Double totalWeight;

    private Double price;

}
