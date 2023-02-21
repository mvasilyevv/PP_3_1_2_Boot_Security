package ru.kata.spring.boot_security.demo.dao.user;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(long id);
    User findByFirstName(String firstName);
    User findByEmail(String email);
    void save(User user);
    void update(User updatedUser, long id);
    void delete(long id);
}
