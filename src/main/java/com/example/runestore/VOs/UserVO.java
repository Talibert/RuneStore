package com.example.runestore.VOs;

import com.example.runestore.types.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO para encapsular um objeto de autenticação
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    @NotBlank
    private String login;

    @NotBlank
    private UserRole userRole;

}
