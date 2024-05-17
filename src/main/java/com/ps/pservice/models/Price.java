package com.ps.pservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "prices")
public class Price extends BaseModel{
    private int price;
    private String currency;
}
