package com.dev.service;

import com.dev.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    User getById(Long id);

    List<User> listUsers();
}
