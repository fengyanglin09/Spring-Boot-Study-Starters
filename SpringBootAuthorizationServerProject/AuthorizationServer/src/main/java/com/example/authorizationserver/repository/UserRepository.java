package com.example.authorizationserver.repository;

import java.util.Optional;

import com.example.authorizationserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
