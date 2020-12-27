package com.ray.repository;

import com.ray.entities.User;

public interface UserRepository {
    
    User login(String username, String password);
}
