package com.ps.pservice.inheritancerelations.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "s_mentors")
public class Mentor extends User {
    private float experience;
    private float rating;
}
