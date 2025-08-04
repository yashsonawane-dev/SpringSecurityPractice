package com.theonewhocodes.springsecurity.service;

import com.theonewhocodes.springsecurity.entity.Users;
import com.theonewhocodes.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminInitializer {

    @Autowired
    private UserRepository userRepository;

    /**
     * CommandLineRunner to initialize the admin user in the database.
     * This method runs at application startup and checks if the admin user exists.
     * If not, it creates a new admin user with the username "admin" and password
     * "admin123", encoded using the provided PasswordEncoder.
     */
    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
        return args -> {
            Users users = new Users();
            users.setUsername("admin");
            users.setPassword(passwordEncoder.encode("admin123"));
            users.setRole("ROLE_ADMIN");
            if (userRepository.findByUsername(users.getUsername()).isEmpty()) {
                userRepository.save(users);
                System.out.println("Admin user created with username: " + users.getUsername());
            } else {
                System.out.println("Admin user already exists with username: " + users.getUsername());
            }
        };
    }
}
