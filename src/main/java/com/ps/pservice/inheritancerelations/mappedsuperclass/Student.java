package com.ps.pservice.inheritancerelations.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "ms_students")
public class Student extends User{
    private String college;
    private float attendance;
}
