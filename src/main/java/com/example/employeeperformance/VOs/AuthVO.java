package com.example.employeeperformance.VOs;

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
public class AuthVO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
