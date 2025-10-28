package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.AuthVO;
import com.example.employeeperformance.VOs.ErrorResponseVO;
import com.example.employeeperformance.VOs.RegisterVO;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.exceptions.invalid.InvalidUserException;
import com.example.employeeperformance.types.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false) // Desabilita os filtros de segurança
public class TesteAuthController extends AbstractControllerTests{

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Vamos fazer login corretamente
     */
    @Test
    void testeLogin() throws Exception {

        AuthVO authVO = new AuthVO("usuario", "senha");
        User user = new User("usuario", "senha", "user@gmail.com", UserRole.USER);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(tokenService.generateToken(user)).thenReturn("token-de-teste");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authVO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-de-teste"));
    }

    /**
     * Vamos tentar fazer login com um erro de credencial
     */
    @Test
    void testeLoginComErroDeCredenciais() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenThrow(new BadCredentialsException("Usuário ou senha inválidos!"));

        ErrorResponseVO erro = new ErrorResponseVO("Usuário ou senha inválidos!");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AuthVO("usuario", "senha"))))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erro)));
    }

    /**
     * Vamos tentar fazer login com um usuário que não existe
     */
    @Test
    void testeLoginComUsernameNotFound() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenThrow(new UsernameNotFoundException("Usuário não encontrado!"));

        ErrorResponseVO erro = new ErrorResponseVO("Usuário não encontrado!");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AuthVO("usuario", "senha"))))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erro)));
    }

    /**
     * Vamos tentar criar um usuário que ainda não existe
     */
    @Test
    void testeRegister() throws Exception {
        Mockito.when(authService.loadUserByUsername("usuario")).thenReturn(null);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRegisterVO())))
                .andExpect(status().isOk());
    }

    /**
     * Vamos tentar criar um usuário que já existe
     */
    @Test
    void testeRegisterUsuarioJaExistente() throws Exception {
        Mockito.doThrow(new InvalidUserException()).when(authService).createUser(Mockito.any());

        ErrorResponseVO erro = new ErrorResponseVO("Login de usuário inválido!");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRegisterVO())))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erro)));
    }

    private RegisterVO getRegisterVO(){
        return new RegisterVO("usuario", "senha", "email@gmail.com", UserRole.USER);
    }
}