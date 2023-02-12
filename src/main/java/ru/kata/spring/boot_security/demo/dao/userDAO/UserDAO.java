package ru.kata.spring.boot_security.demo.dao.userDAO;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserDAO {

    List<User> findAll();
    User findById(long id);
    User findByUsername(String username);
    void save(User user);
    void update(User updatedUser, long id);
    void delete(long id);
}
