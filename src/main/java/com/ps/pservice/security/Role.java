package com.ps.pservice.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role{
    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;
}
