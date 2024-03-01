package com.ps.pservice.inheritancerelations.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "j_students")
public class Student extends User {
    private String college;
    private float attendance;
}
