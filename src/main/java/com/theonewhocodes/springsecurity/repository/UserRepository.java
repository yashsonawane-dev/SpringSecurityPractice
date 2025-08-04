package com.theonewhocodes.springsecurity.repository;

import com.theonewhocodes.springsecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}
