package com.ps.pservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "price_id")
    private Price price;
}
