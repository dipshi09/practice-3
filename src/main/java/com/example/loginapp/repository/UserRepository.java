package com.example.loginapp.repository;

import com.example.loginapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}

