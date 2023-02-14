package ru.kata.spring.boot_security.demo.dao.role_dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
public interface RoleDAO {

    List<Role> findAll();
    Role findById(long id);
    Role findByRoleTitle(String roleTitle);
    Role createIfNotContains(String roleTitle);
}
