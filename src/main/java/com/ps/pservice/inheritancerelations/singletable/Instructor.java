package com.ps.pservice.inheritancerelations.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "s_instructors")
public class Instructor extends User {
    private String company;
    private float salary;
}
