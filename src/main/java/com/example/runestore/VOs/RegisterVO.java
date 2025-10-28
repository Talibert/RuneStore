package com.example.runestore.VOs;

import com.example.runestore.types.UserRole;

/**
 * VO para encapsular um objeto de registro
 */
public record RegisterVO (String login, String password, String email, UserRole userRole)  {
}
