package ru.kata.spring.boot_security.demo.service.user;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    void save(User user);
    void update(User updatedUser, long id);
    void delete(long id);
}
