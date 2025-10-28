package com.example.employeeperformance.types;

import java.util.List;

/**
 * Esse enum vai definir as roles que cada usuário pode ter acesso.
 * Cada enum devolve uma lista com os tipos de acesso permitidos
 *
 * Essa lista é resgatada no getAuthorities da classe User e o Spring-Security usa elas para validar no filter-chain e pre-authorize
 */
public enum UserRole {

    ADMIN(List.of("ROLE_ADMIN", "ROLE_USER")),
    USER(List.of("ROLE_USER"));

    private final List<String> roles;

    UserRole(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}
