package com.example.runestore.services.security;

import com.example.runestore.VOs.RegisterVO;
import com.example.runestore.entities.User;
import com.example.runestore.exceptions.invalid.InvalidUserException;
import com.example.runestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return userRepository.findByEmail(usernameOrEmail)
                .or(() -> userRepository.findByNickName(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
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
