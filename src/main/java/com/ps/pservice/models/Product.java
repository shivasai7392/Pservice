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
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category category;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false)
    private Price price;
}
