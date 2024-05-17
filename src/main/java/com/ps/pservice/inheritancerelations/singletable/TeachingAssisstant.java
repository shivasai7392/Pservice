package com.ps.pservice.inheritancerelations.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "s_teaching_assistants")
public class TeachingAssisstant extends User {
    private String college;
    private float salary;
}
