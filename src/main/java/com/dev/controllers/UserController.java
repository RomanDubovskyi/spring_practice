package com.dev.controllers;

import com.dev.UserResponseDto;
import com.dev.model.User;
import com.dev.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/inject", method = RequestMethod.GET)
    public void inject() {
        for (int i = 1; i < 5; i++) {
            User newUser = new User();
            newUser.setLogin("login " + i);
            newUser.setPassword("password " + i);
            userService.add(newUser);
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return transferUserToDto(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userService.listUsers();
        return users.stream().map(this::transferUserToDto).collect(Collectors.toList());
    }

    private UserResponseDto transferUserToDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setPassword(user.getPassword());
        userDto.setLogin(user.getLogin());
        return userDto;
    }
}
