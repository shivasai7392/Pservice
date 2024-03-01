package com.ps.pservice.inheritancerelations.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "j_mentors")
public class Mentor extends User {
    private float experience;
    private float rating;
}
