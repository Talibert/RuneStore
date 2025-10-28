package com.example.employeeperformance.config;

import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.repositories.UserRepository;
import com.example.employeeperformance.types.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * O objetivo dessa classe de config é popular o banco, caso ele esteja vazio, quando a aplicação subir
 */
@Configuration
public class DataLoaderConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoaderConfig.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Vamos criar, por padrão, um usuário Admin
     */
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> usuariosIniciais = new ArrayList<>();
                usuariosIniciais.add(getUser("Guilherme", "guilherme@gmail.com", UserRole.ADMIN));
                usuariosIniciais.add(getUser("Malu", "malu@gmail.com", UserRole.USER));

                usuariosIniciais.forEach(user -> {
                    LOGGER.warn("Criando usuário {} no banco", user.getNickName());

                    userRepository.save(user);

                    LOGGER.warn("Usuário criado.");
                });

            }
        };
    }

    private User getUser(String login, String email, UserRole userRole){
        String password = passwordEncoder.encode("Senha1234*");

        return new User(login, password, email, userRole);
    }
}
