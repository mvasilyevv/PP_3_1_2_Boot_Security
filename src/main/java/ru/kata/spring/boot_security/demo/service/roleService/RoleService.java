package ru.kata.spring.boot_security.demo.service.roleService;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
public interface RoleService {

    List<Role> findAll();
    Role findById(long id);
    Role findByRoleTitle(String roleTitle);
}
