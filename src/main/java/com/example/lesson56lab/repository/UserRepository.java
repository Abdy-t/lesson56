package com.example.lesson56lab.repository;

import com.example.lesson56lab.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User getById(String id);
}
