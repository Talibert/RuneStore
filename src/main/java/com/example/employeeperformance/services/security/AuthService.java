package com.example.employeeperformance.services.security;

import com.example.employeeperformance.VOs.RegisterVO;
import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.exceptions.invalid.InvalidUserException;
import com.example.employeeperformance.exceptions.notfound.UserNotFoundException;
import com.example.employeeperformance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service que serve para gerenciar a lógica de autenticação dos usuários
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Vamos verificar a existência de um usuário pelo username e retorná-lo
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        Optional<User> userByNickName = userRepository.findByNickName(usernameOrEmail);

        if(userByNickName.isPresent())
            return userByNickName.get();

        Optional<User> userByEmail = userRepository.findByEmail(usernameOrEmail);

        if(userByEmail.isPresent())
            return userByEmail.get();

        throw new UserNotFoundException();
    }

    /**
     * Cria um novo usuário
     */
    public void createUser(RegisterVO registerVO){
        if(userRepository.findByEmail(registerVO.email()).isPresent())
            throw new InvalidUserException("Email já está em uso");

        String encryptedPassword = passwordEncoder.encode(registerVO.password());
        User user = new User(registerVO.login(), encryptedPassword, registerVO.email(), registerVO.userRole());

        this.userRepository.save(user);
    }
}
