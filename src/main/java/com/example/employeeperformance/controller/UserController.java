package com.example.employeeperformance.controller;

import com.example.employeeperformance.VOs.UserVO;
import com.example.employeeperformance.services.UserService;
import com.example.employeeperformance.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint para retornar a lista de usuários cadastrados no banco
     *
     * Esse metodo pode ou não receber um filter de role.
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) UserRole userRole){
        List<UserVO> userList = userService.findAllWithFilter(userRole);

        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
