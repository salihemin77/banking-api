package com.example.banking_api.service;

import com.example.banking_api.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Integer id);
    void deleteById(Integer id);
    List<User> findAll();

}
