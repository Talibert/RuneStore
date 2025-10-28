package com.example.employeeperformance.VOs;

import com.example.employeeperformance.types.UserRole;

/**
 * VO para encapsular um objeto de registro
 */
public record RegisterVO (String login, String password, String email, UserRole userRole)  {
}
