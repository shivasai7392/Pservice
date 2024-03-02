package com.ps.pservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
