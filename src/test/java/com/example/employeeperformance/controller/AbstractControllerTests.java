package com.example.employeeperformance.controller;

import com.example.employeeperformance.repositories.UserRepository;
import com.example.employeeperformance.services.security.AuthService;
import com.example.employeeperformance.services.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Essa classe abstrata é para preparar as classes referentes a segurança quando tentarmos acessar algum endpoint
 */
public class AbstractControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected TokenService tokenService;

    @MockBean
    protected AuthenticationManager authenticationManager;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected UserRepository userRepository;

}
