package com.dev;

import com.dev.config.AppConfig;
import com.dev.model.User;
import com.dev.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        userService.add(user);

        User user2 = new User();
        user2.setLogin("another user");
        user2.setPassword("another user");
        userService.add(user2);
        System.out.println(userService.listUsers());
    }
}
