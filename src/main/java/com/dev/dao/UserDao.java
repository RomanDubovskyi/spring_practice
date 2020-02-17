package com.dev.dao;

import com.dev.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User getById(Long id);

    List<User> listUsers();

}
