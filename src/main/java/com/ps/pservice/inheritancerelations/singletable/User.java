package com.ps.pservice.inheritancerelations.singletable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Entity(name = "s_users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;
}
