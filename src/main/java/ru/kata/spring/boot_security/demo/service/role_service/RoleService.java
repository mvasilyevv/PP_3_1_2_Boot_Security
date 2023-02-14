package ru.kata.spring.boot_security.demo.service.role_service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
public interface RoleService {

    List<Role> findAll();
    Role findById(long id);
    Role findByRoleTitle(String roleTitle);
    Role createIfNotContains(String roleTitle);
}
