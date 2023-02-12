package ru.kata.spring.boot_security.demo.dao.roleDAO;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
public interface RoleDAO {

    List<Role> findAll();
    Role findById(long id);
    Role findByRoleTitle(String roleTitle);
}
