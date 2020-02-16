package com.dev.controllers;

import com.dev.UserResponseDto;
import com.dev.model.User;
import com.dev.service.UserService;

import java.util.ArrayList;
import java.util.List;

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
            User firstUser = new User();
            firstUser.setLogin("login " + i);
            firstUser.setPassword("password " + i);
            userService.add(firstUser);
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.getById(userId);
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(userId);
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userService.listUsers();
        List<UserResponseDto> usersToReturn = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userDto = new UserResponseDto();
            userDto.setId(user.getId());
            userDto.setPassword(user.getPassword());
            userDto.setLogin(user.getLogin());
            usersToReturn.add(userDto);
        }
        return usersToReturn;
    }
}
