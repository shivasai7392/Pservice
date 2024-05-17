package com.ps.pservice.inheritancerelations.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "ms_instructors")
public class Instructor extends User{
    private String company;
    private float salary;
}
