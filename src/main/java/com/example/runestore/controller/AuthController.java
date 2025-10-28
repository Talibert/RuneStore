package com.example.runestore.controller;

import com.example.runestore.VOs.AuthVO;
import com.example.runestore.VOs.LoginResponseVO;
import com.example.runestore.VOs.RegisterVO;
import com.example.runestore.VOs.ResponseVO;
import com.example.runestore.entities.User;
import com.example.runestore.services.security.AuthService;
import com.example.runestore.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint para fazer o login de um usuário.
     * A ideia é receber um VO com as credenciais e realizar o processo de autenticação do SpringSecurity
     *
     * Caso o login seja correto, vamos retornar um token para o front que deve ser utilizado no Header das requisições
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthVO authVO){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authVO.getLogin(), authVO.getPassword());

        // Duas exceções podem ser lançadas aqui: BadCredentials ou UsernameNotFound
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseVO(token));
    }

    /**
     * Endpoint de registro.
     * Vamos receber um VO com os dados de registro e validar a existência ou não de um usuário com o mesmo userName
     *
     * Caso a validação passe, vamos criar o usuário no banco
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity register(@RequestBody @Valid RegisterVO registerVO){
        this.authService.createUser(registerVO);

        return ResponseEntity.ok(new ResponseVO("Usuário " + registerVO.login() + " criado com sucesso!"));
    }
}
