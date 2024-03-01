package com.ps.pservice.inheritancerelations.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_mentors")
public class Mentor extends User{
    private float experience;
    private float rating;
}
