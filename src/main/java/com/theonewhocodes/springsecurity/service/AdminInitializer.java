package com.theonewhocodes.springsecurity.service;

import com.theonewhocodes.springsecurity.dto.Role;
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
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            if (userRepository.findByUsername(admin.getUsername()).isEmpty()) {
                userRepository.save(admin);
                System.out.println("Admin user created with username: " + admin.getUsername());
            } else {
                System.out.println("Admin user already exists with username: " + admin.getUsername());
            }

            Users user = new Users();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(Role.USER);
            if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
                userRepository.save(user);
                System.out.println("User user created with username: " + user.getUsername());
            } else {
                System.out.println("User user already exists with username: " + user.getUsername());
            }
        };
    }
}
