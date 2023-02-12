package ru.kata.spring.boot_security.demo.service.roleService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.roleDAO.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleDAO.findById(id);
    }

    @Override
    public Role findByRoleTitle(String roleTitle) {
        return roleDAO.findByRoleTitle(roleTitle);
    }
}
