package com.example.runestore.controller;

import com.example.runestore.VOs.ErrorResponseVO;
import com.example.runestore.VOs.UserVO;
import com.example.runestore.exceptions.notfound.UserNotFoundException;
import com.example.runestore.services.UserService;
import com.example.runestore.types.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class TesteUserController extends AbstractControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Vamos buscar os usuários sem um filtro explicito
     */
    @Test
    void testeGetUsers() throws Exception {

        UserVO user1 = new UserVO("usuario1", UserRole.USER);
        UserVO user2 = new UserVO("usuario2", UserRole.ADMIN);

        List<UserVO> userList = List.of(user1, user2);

        Mockito.when(userService.findAllWithFilter(null)).thenReturn(userList);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userList)));
    }

    /**
     * Vamos buscar os usuários com um filtro
     */
    @Test
    void testeGetUsersComFiltro() throws Exception {

        UserVO user1 = new UserVO("usuario1", UserRole.USER);
        UserVO user2 = new UserVO("usuario2", UserRole.ADMIN);

        List<UserVO> userList = List.of(user2);

        Mockito.when(userService.findAllWithFilter(UserRole.ADMIN)).thenReturn(userList);

        mockMvc.perform(get("/user")
                        .param("userRole", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userList)));
    }

    /**
     * Vamos fazer login corretamente
     */
    @Test
    void testeGetUsersComErro() throws Exception {

        Mockito.when(userService.findAllWithFilter(null)).thenThrow(new UserNotFoundException());

        ErrorResponseVO erro = new ErrorResponseVO("Nenhum usuário encontrado!");

        mockMvc.perform(get("/user"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erro)));
    }
}