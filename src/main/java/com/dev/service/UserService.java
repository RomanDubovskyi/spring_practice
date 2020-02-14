package com.dev.service;

import com.dev.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();
}
