package com.ps.pservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JWTObject {
    // This is kind of a DTO object so that when a token is validated
    // and you want some information from token then specify all attributes
    // needed here

    private String email;
    private UUID userId;
    private List<Role> roles;
    private String token;
}
