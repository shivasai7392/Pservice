package com.ps.pservice.inheritancerelations.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "tpc_mentors")
public class Mentor extends User {
    private float experience;
    private float rating;
}
