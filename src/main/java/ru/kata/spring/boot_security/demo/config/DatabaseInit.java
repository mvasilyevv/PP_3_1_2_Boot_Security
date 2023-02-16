package ru.kata.spring.boot_security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.user.UserService;
@Component
public class DatabaseInit {
    private final UserService userService;
    @Autowired
    public DatabaseInit(UserService userService) {
        this.userService = userService;
    }

    public void addFirstUser() {
        Role role = new Role("ROLE_ADMIN");
        User user = new User("Admin", "admin", 1993, "admin@gmail.com");
        user.setRole(role);
        userService.save(user);
    }
}
