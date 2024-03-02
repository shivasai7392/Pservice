package com.ps.pservice.inheritancerelations.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "j_instructors")
public class Instructor extends User {
    private String company;
    private float salary;
}
